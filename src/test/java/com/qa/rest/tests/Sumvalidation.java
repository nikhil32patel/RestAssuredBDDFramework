package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.rest.files.payload;
import io.restassured.path.json.JsonPath;

public class Sumvalidation {

	@Test
	public void sumOfCourses()
	{
		JsonPath js =new JsonPath(payload.CoursePrice());
		// PRINT COURSE SIZE
		
		int sum =0;
		int coursesize = js.getInt("courses.size()");
		System.out.println("Total Coursesize :"+coursesize);
		
		for(int i =0;i<coursesize;i++)
		{
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			System.out.println(amount);
			sum = sum + amount;
		}
		
		int pa = js.getInt("dashboard.purchaseAmount");
		System.out.println("Sum of course Expected:"+sum);
		System.out.println("Sum of course Actual:"+pa);
		Assert.assertEquals(pa, sum);
		
		
	}
}
