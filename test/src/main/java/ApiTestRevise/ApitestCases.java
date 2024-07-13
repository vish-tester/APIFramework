package ApiTestRevise;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import pojoForInput.Booking;
import utils.ExtentReportsManager;
import utils.FileConstants;
import utils.RetryClass;

public class ApitestCases {

	public String String;
	public Booking book;
	// public Response res;
	public int bookid;
	public String authenKey;
	public String jwtToken;
	public String putRequest;
	public String patchRequest;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeClass
	public void premsteps() throws IOException {
	extent =	ExtentReportsManager.configExtentReports();
		test = extent.createTest("This suite tests everything end to end");
		String = FileUtils.readFileToString(new File(FileConstants.FILE_JSON_REQUEST), "UTF-8");

		jwtToken = FileUtils.readFileToString(new File(FileConstants.FILE_JWT_TOKEN), "UTF-8");

		putRequest = FileUtils.readFileToString(new File(FileConstants.FILE_PUT_REQUEST), "UTF-8");

		patchRequest = FileUtils.readFileToString(new File(FileConstants.FILE_PATCH_REQUEST), "UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
		book = mapper.readValue(String, Booking.class);

	}

	@Test
	public void postAPI() {

		test.log(Status.INFO,"We have come into postAPI");
		Response res = RestAssured.given().contentType(ContentType.JSON).body(String).when()
				.post("https://restful-booker.herokuapp.com/booking").then().extract().response();

		String checkindate = res.path("booking.bookingdates.checkin");
		Assert.assertEquals(book.getBookingdates().getcheckin(), checkindate);

		JSONArray arr = JsonPath.read(res.body().asString(), "$..bookingid");
		bookid = (Integer) arr.get(0);
		test.log(Status.PASS,"API successfully posted the data.");
		System.out.println(bookid);

	}

	@Test(dependsOnMethods = "postAPI")
	public void getAfterPost() {

		test.log(Status.INFO,"We have come into GetAPI.");
		RestAssured.given().baseUri("https://restful-booker.herokuapp.com/booking").when().get("/{book_id}", bookid)
				.then().assertThat().statusCode(200)
				.body("bookingdates.checkin", Matchers.equalTo(book.getBookingdates().getcheckin()));
		test.log(Status.PASS,"API successfully fetched the data.");

	}

	@Test(dependsOnMethods = "getAfterPost")
	public void getJWT() {

		test.log(Status.INFO,"We have come into JWT.");
		Response res2 = RestAssured.given().contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com/auth").body(jwtToken).when().post().then().assertThat()
				.statusCode(200).extract().response();

		authenKey = res2.path("token");
		System.out.println(authenKey);
		test.log(Status.PASS,"Key Generation successfully");

	}

	@Test(dependsOnMethods = "getJWT")
	public void getPut() {

		test.log(Status.INFO,"We have come into Put.");
		Response res3 = RestAssured.given().contentType(ContentType.JSON).header("Cookie", "token=" + authenKey)
				.baseUri("https://restful-booker.herokuapp.com/booking").body(putRequest).when()
				.put("/{book_id}", bookid).then().assertThat().statusCode(200).extract().response();

		System.out.println(res3.body().asString());
		test.log(Status.PASS,"Data updated successfully");

	}
	
	@Test(dependsOnMethods= "getPut")
	public void patchReq() {
		
		test.log(Status.INFO,"We have come into Patch.");
		RestAssured.given().contentType(ContentType.JSON)
		.header("Cookie", "token=" + authenKey).baseUri("https://restful-booker.herokuapp.com/booking")
		.body(patchRequest).when()
		.patch("/{book_id}", bookid)
		.then()
		.assertThat()
		.body("firstname", Matchers.equalTo("Testers Talk"))
		.extract().response();
		test.log(Status.PASS,"Data patched successfully");
		
		
	}
	
	@Test(dependsOnMethods="patchReq") 
	public void delReq() {
		test.log(Status.INFO,"We have come into Delete.");
		RestAssured.given()
		.header("Cookie", "token=" + authenKey)
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
		.delete("/{book_id}", bookid)
		.then()
		.assertThat()
		.statusCode(201);
		
		test.log(Status.PASS,"Data deleted successfully");
	}
	
	
	@Test(dependsOnMethods="delReq")
	public void checkReq() {
	
		test.log(Status.INFO,"We need to check if data is deleted.");
		Response res4 = 	RestAssured.given().contentType(ContentType.JSON)
		.when()
		.get("https://restful-booker.herokuapp.com/booking")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response();
		
		
		Assert.assertFalse(res4.body().asString().contains(Integer.toString(bookid)),"data deletion was not successful");
		test.log(Status.PASS,"Data deleted successfully");
	}

	
	@AfterClass(alwaysRun=true)
	public void cleanUp() {
		extent.flush();
	}

}
