package com.qa.rest.tests;

import com.qa.rest.files.payload;

import groovyjarjarantlr.CppCodeGenerator;
import io.restassured.path.json.JsonPath;

public class NestedJsonParseExample {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		JsonPath js =new JsonPath(payload.CoursePrice());
		
		// PRINT COURSE SIZE
		int coursesize = js.getInt("courses.size()");
		System.out.println(coursesize);
		
		// PRINT TOTAL AMOUNT
		int totalamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalamount);
		
		// PRINT THIRD COUSRSE TITLE
		String titaloffirstcource =  js.get("courses.title[2]");
		System.out.println(titaloffirstcource);
		
		int sumofallcource = 0;
		// PRINT ALL COURSES TITLE AND PRICE
		for(int i = 0;i<coursesize;i++){
			String CouurseTitle = js.get("courses["+i+"].title");
			int CoursePrice = js.getInt("courses["+i+"].price");
			System.out.println("Titile of Course: "+CouurseTitle);
			System.out.println("Price of Course: "+CoursePrice);
			
		}
		
		// PRINT NO COPIES SOLD BY RPA Course
		System.out.println("Print No of copies sold by RPA Course");
		for(int i = 0;i<coursesize;i++){
			String CouurseTitle = js.get("courses["+i+"].title");
			if(CouurseTitle.equalsIgnoreCase("RPA"))
			{
				int copies = js.get("courses["+i+"].copies");
				System.out.println("No of Copies Sold by RPA Course is:"+copies);
				break;
			}
		}
	}
}
