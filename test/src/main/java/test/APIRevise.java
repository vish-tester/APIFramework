package test;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class APIRevise {
	
	JSONObject booking;
	
	@BeforeClass
	
	public void prep() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		 booking = new JSONObject();
		JSONObject bookingdates = new JSONObject();
		
		booking.put("firstname", "vishal");
		booking.put("lastname", "singh");
		booking.put("totalprice", 100);
		booking.put("depositpaid", true);
		booking.put("additionalneeds", "Breakfast");
		booking.put("bookingdates", bookingdates);
		
		bookingdates.put("checkin", "2024-07-10");
		bookingdates.put("checkout", "2024-07-11");
	}
	
	@Test
	public void getData() {
		
	Response res = 	RestAssured.given()
		.contentType(ContentType.JSON)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
		.get()
		.then()
		.statusCode(200)
		.extract()
		.response();
	
	//JsonPath obj = res.getBody().jsonPath();
	
	
	}
	
	@Test
	public void postData() {
	Response res= 	RestAssured.given()
		.contentType(ContentType.JSON)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.body(booking.toJSONString())
		.when()
		.post()
		.then()
		.assertThat()
		.statusCode(200)
		.log().all()
		.body("booking.firstname", Matchers.equalTo(booking.get("firstname")))
		.extract().response();
	
	System.out.println(res.getCookie("Content-Type"));
	
	int bookingid = res.path("bookingid");
	
 RestAssured.given()
	.contentType(ContentType.JSON)
	.pathParam("bookingId", bookingid)
	.baseUri("https://restful-booker.herokuapp.com/booking")
	.when()
	.get("{bookingId}")
	.then()
	.assertThat()
	.statusCode(200)
	.body("bookingdates.checkin", Matchers.equalTo("2024-07-10"));
	

	
	
	}
	
	


}
