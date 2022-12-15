package com.qa.rest.tests;
import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class oAuthTest {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","S:\\SOFTWARES\\Selenium\\WebDrivers\\Chrome\\ChromeDriver 94.0.4606.41\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//daynamic wait
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		String response = given().queryParam("access_token", "").
		when().log().all().
		get("https://rahulshettyacademy.com/getCourse.php").asString(); 
		
		System.out.println(response);
		
	}
	
	
}
