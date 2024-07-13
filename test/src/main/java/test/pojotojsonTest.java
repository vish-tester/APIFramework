package test;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import pojoForInput.Booking;
import pojoForInput.bookingDates;

public class pojotojsonTest {

	@Test
	public void postReq() throws JsonProcessingException {
		
		bookingDates bookingdates = new bookingDates("2024-08-21", "2024-08-25");
		Booking book = new Booking("vishal", "singh", 1000, true, "nothing", bookingdates);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
		
		//de-serialize
		
		Booking bokkret = mapper.readValue(body, Booking.class);
		
		System.out.println(bokkret.getBookingdates().getcheckout());
		
		
		Response res = RestAssured.given().contentType(ContentType.JSON)
		.baseUri("https://restful-booker.herokuapp.com/booking").body(body).when().post()
		.then()
		.assertThat()
		.body("booking.firstname", Matchers.equalTo("vishal"))
		.statusCode(200).extract().response();
		
JSONArray bookingid = 	 JsonPath.read(res.body().asString(), "$..bookingid");
		
		
		int bookid = (Integer)bookingid.get(0);
		
		
	List<String> ls = 	 RestAssured.given().contentType(ContentType.JSON)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.when().get("/{booking-id}",bookid)
		.jsonPath().getList("booking");
		
		System.out.println(ls);
		
	}
	
	
	
	
}
