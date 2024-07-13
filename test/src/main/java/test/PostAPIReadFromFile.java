package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import utils.FileConstants;

public class PostAPIReadFromFile {

	@Test
	public void postData(){
		
		try {
			String body = FileUtils.readFileToString(new File(FileConstants.FILE_JSON_REQUEST), "UTF-8");
		//	System.out.println(body);
			
		Response res= 	RestAssured.given()
			.contentType(ContentType.JSON)
			.baseUri("https://restful-booker.herokuapp.com/booking")
			.body(body)
			.when()
			.post()
			.then()
			//.log().all()
			.assertThat()
			.statusCode(200).body("body", null)
			.body("booking.firstname", Matchers.equalTo("Vishal"))
			.extract()
			.response();
			
		int a =	res.path("");
		
	JSONArray jarr = JsonPath.read(res.body().asString(), "$.booking..bookingdates..checkin");
		
	String str = (String) jarr.get(0);
	System.out.println(str);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}
	
	
}
