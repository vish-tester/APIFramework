package test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;


public class APITesting {
	
	JSONObject booking ;
	JSONObject bookingDate;
	
	
	
	
	@BeforeTest
	private void prepData () {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
	 booking = new JSONObject();
	 bookingDate = new JSONObject();
	
	booking.put("firstname", "vishal");
	booking.put("lastname", "singh");
	booking.put("totalprice", 100);
	booking.put("depositpaid", true);
	booking.put("additionalneeds", "Breakfast");
	booking.put("bookingdates", bookingDate);
	
	bookingDate.put("checkin", "2024-07-10");
	bookingDate.put("checkout", "2024-07-11");
	
	
	}
	
	
	
	
	@Test(enabled = false)
	public void getData() {
		Response res = RestAssured.given()
		.contentType(ContentType.JSON)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
		.get()
		.then()
		.assertThat()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.header("Content-Type", "application/json; charset=utf-8")
		.extract().response();
		
		System.out.println(res.getBody().asString());
	}
	
	@Test
	public void postData()
	{
		RestAssured.given()
		.header("Content-Type","application/json")
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.body(booking.toString())
		//.log().all()
		.when()
		.post()
		.then()
		//.log().ifValidationFails()
		.statusCode(200)
		.body("booking.firstname", Matchers.equalTo(booking.get("firstname")))
		.body("booking.bookingdates.checkin", Matchers.equalTo(bookingDate.get("checkin")));
	}

}
