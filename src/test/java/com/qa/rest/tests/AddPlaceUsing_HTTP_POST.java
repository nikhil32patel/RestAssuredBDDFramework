package com.qa.rest.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.qa.rest.files.*;

import org.testng.Assert;

//import files.ReUsableMethods;
//import files.payload;
public class AddPlaceUsing_HTTP_POST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		//given - all input details 
		
		//when - Submit the API -resource,http method
		
		//Then - validate the response
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		String Response = 
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
		
		.when().post("maps/api/place/add/json").
		
		then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).
		header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		System.out.println(Response);
		
		JsonPath js = new JsonPath(Response);
		String place_id = js.getString("place_id");
	
		System.out.println(place_id);
	
	
	}

}
