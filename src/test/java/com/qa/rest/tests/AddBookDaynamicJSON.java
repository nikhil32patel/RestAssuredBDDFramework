package com.qa.rest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.rest.files.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

public class AddBookDaynamicJSON {
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn,String aisle) {
		
		
		RestAssured.baseURI = "http://216.10.245.166";
		String res = given().log().all().header("Content-Type","application/json").
		body(payload.addbook(isbn,aisle)).
		when().
		post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		
		JsonPath js = ReUsbleMethods.rawToJason(res);
		String id = js.get("ID");
		System.out.println(id);
			
	}
	
	
	@DataProvider(name = "BooksData")
	public Object[][] getData(){
		return new Object[][] {{"baba","1188"},{"caca","2299"},{"dada","3399"}};
		
	}
}
