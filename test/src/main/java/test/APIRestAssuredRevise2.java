package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import utils.FileConstants;

public class APIRestAssuredRevise2 {

	String body;
	@BeforeClass
	
	public void beforeclass() throws IOException {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		 body = FileUtils.readFileToString(new File(FileConstants.FILE_JSON_REQUEST),"UTF-8");
		
		
	}
	
	@Test (enabled = false)
	public void getRequest() {
		
	Response res =	RestAssured.given()
		.contentType(ContentType.JSON)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
		.get()
		.then()
		.assertThat()
		.statusCode(200).extract().response();
	JSONArray arr = JsonPath.read(res.body().asString(), "$..bookingid");
	
	System.out.println(arr.size());
	}
	
	@Test 
	public void postRequest() {
		
	Response res = 	RestAssured.given()
		.contentType(ContentType.JSON)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.body(body)
		.when()
		.post()
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response();
	
	int bookid = res.path("bookingid");
	
	RestAssured.given().contentType(ContentType.JSON)
	.baseUri("https://restful-booker.herokuapp.com/booking")
	.when()
	.get("/{bookingid}",bookid)
	.then()
	.assertThat()
	.statusCode(200)
	.body("firstname", Matchers.equalTo("Vishal"));
		
	}
	
}
