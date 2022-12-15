package com.qa.rest.files;

import io.restassured.path.json.JsonPath;

public class ReUsbleMethods {

		public static JsonPath rawToJason(String response)
		{
			JsonPath jsnraw = new JsonPath(response);
			return jsnraw;
		}
}
