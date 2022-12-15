package com.qa.rest.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;

import com.qa.rest.files.ReUsbleMethods;
import com.qa.rest.files.payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class UpdatePlaceUsing_HTTP_PUT {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		// validate if Add Place API is workimg as expected 
		// Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		// given - all input details 
		// when - Submit the API -resource,http method
		// Then - validate the response

		//ADD NEW PLACE USING HTTP POST
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json").
				then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).
				header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println(Response);
		JsonPath js = new JsonPath(Response);
		String place_id = js.getString("place_id");
		System.out.println(place_id);
		
		//UPDATE PLACE USING PLACE ID AND VERIFY SAME PLACE ID IS UPDATED WHICH WAS ADDED EARLIER
		//USING HTTP PUT
		String newaddresh = "Chakala Andheri East Mumbai - 400093";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\""+newaddresh+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"").when().put("maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		//header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		//GET UPDATED PLACE USIING HTTP GET
		String getPlaceResponse = given().log().all().queryParam("key","qaclick123")
		.queryParam("place_id", place_id)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		JsonPath jsaddresh = ReUsbleMethods.rawToJason(getPlaceResponse);
		String actualaddresh = jsaddresh.getString("address");
		System.out.println(actualaddresh);
		Assert.assertEquals(actualaddresh,newaddresh,"VERIFY UPDATED ADDRESH");
		//Assert.assertEquals(actualaddresh,"WRONG ADDRESH","VERIFY UPDATED ADDRESH");
		
		
	}
}
