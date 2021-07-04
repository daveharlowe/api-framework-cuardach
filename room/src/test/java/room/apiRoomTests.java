package room;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class apiRoomTests {
	String baseURLForRBP = "http://192.168.0.189";
	private String cookieValue;
	
	@Before
	public void getLoginToken_10000()
	{   
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("username", "admin"); 
		requestParams.put("password", "password");
		
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://vm102:3004/auth";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.POST, "/login");
		//cookieValue = response.getCookie("\"token\"");
		
		//System.out.println("Cookie is =>  " +  cookieValue);
			
		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		//{"token":"MVm5LNbnSTB8U5WC"}
		//0123456789012345678901234567 
		setCookieValue("token="+ responseBody.substring(10,26));
	//	System.out.println("Cookie is =>  " +  getCookieValue());
		System.out.println("Response Body is =>  " + responseBody);

		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(200,statusCode);

	}

	// Test to retrieve all the details of rooms setup on the "room" microservice.
	@Test
	public void getRoomDetails_10001()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://vm102:3001";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/room");
			
		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(200,statusCode);

	}
	
	@Test
	public void createRoom_10002_ver2()
	{
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\r\n  \"accessible\": false,\r\n  \"description\": \"Description of room, created using TAR_Okhttp3 client\",\r\n  \"features\": [],\r\n  \"image\": \"room2.jpg\",\r\n  \"roomNumber\": 111,\r\n  \"roomPrice\": 20,\r\n  \"roomid\": 111,\r\n  \"type\": \"Single\"\r\n}");
				Request request = new Request.Builder()
				  .url("http://vm102:3001/room/")
				  .method("POST", body)
				  .addHeader("Accept-Language", "en-US,en;q=0.9")
				  .addHeader("Cookie", getCookieValue())
				  .addHeader("Content-Type", "application/json")
				  .build();
				
			    okhttp3.Response response=null;
			    
				try {
					response = client.newCall(request).execute();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//		//		int statusCode = response.getStatusCode();
				int statusCode = response.code();
				
				try {
					System.out.println("Test 10002_ver2 Response Body is =>  " + response.body().string());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Assert.assertEquals(201,statusCode);
	}
	
	
	@Test
	public void createRoom_10002_1_ver2()
	{
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\r\n  \"accessible\": false,\r\n  \"description\": \"Description of room, created using TAR_Okhttp3 client\",\r\n  \"features\": [],\r\n  \"image\": \"room2.jpg\",\r\n  \"roomNumber\": 111,\r\n  \"roomPrice\": 20,\r\n  \"roomid\": 111,\r\n  \"type\": \"Single\"\r\n}");
				Request request = new Request.Builder()
				  .url("http://vm102:3001/room/")
				  .method("POST", body)
				  .addHeader("Accept-Language", "en-US,en;q=0.9")
				  .addHeader("Cookie", getCookieValue())
				  .addHeader("Content-Type", "application/json")
				  .build();
				
			    okhttp3.Response response=null;
			    
				try {
					response = client.newCall(request).execute();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//		//		int statusCode = response.getStatusCode();
				int statusCode = response.code();
				
				try {
					System.out.println("Test 10002_ver2 Response Body is =>  " + response.body().string());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Assert.assertEquals(201,statusCode);
	}
	
	
	
	
	
	/*
	@Test
	public void createRoom_10002()
	{   
		JSONObject requestParams = new JSONObject();
		
		
//		  "accessible": false,
//		  "description": "Description of room, created using POSTMAN client",
//		  "features": ["TV", "WIFI"],
//		  "image": "https://www.mwtestconsultancy.co.uk/img/testim/room2.jpg",
//		  "roomNumber": 111,
//		  "roomPrice": 20,
//		  "roomid": 111,
//	      "type": "Single" 
		
		requestParams.put("accessible", false); 
		requestParams.put("description", "Description of room, created using TAF/RestAssured client");
		requestParams.put("features", "[]"); 
		requestParams.put("image", "room2.jpg");
		requestParams.put("roomNumber", 119); 
		requestParams.put("roomPrice", 20);
		requestParams.put("roomid", 119);
		requestParams.put("type", "Single");
		
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://192.168.0.189:3001";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		*/
		 // httpRequest.header("Accept", "*/*");
	/*
		System.out.println("Cookie-createRoom is =>  " +  getCookieValue());
		 // httpRequest.header("Cookie", getCookieValue());
		httpRequest.header("Cookie", "token=z1hslSMaCM879lj9");
		
		httpRequest.body(requestParams.toJSONString());
		String printHeaders = requestParams.toString();
		System.out.println("Headers-createRoom are =>  " +  printHeaders);

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.POST, "/room");
		
			
		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(201,statusCode);

	} */

	
	
	
public void setCookieValue(String cookieValue) {
	this.cookieValue = cookieValue;
}
	
public String getCookieValue() {
	return this.cookieValue;
}
	

}
