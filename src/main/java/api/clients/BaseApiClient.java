package api.clients;

import api.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApiClient {

	protected static String Uri = "https://report-ease-prj-666-naa.vercel.app";
	protected static RequestSpecification requestSpec;
	
	static {
		RestAssured.baseURI = Uri;
		requestSpec = RestAssured.given().header("Content-Type","application/json");
		
		

	}
	public static RequestSpecification getRequestSpec()
	{
		 RequestSpecBuilder builder = new RequestSpecBuilder()
	                .setContentType(ContentType.JSON);

	        if (TokenManager.getToken() != null) {
	            builder.addHeader("Authorization", "Bearer " + TokenManager.getToken());
	        }

	        return builder.build();
	    
	}
	
	public static Response SendGetRequest(String endpoint)
	{
		return RestAssured.given().spec(getRequestSpec()).
				when()
				.get(endpoint);
		
	}
	
	public static Response SendPostRequest(String endpoint, Object requestBody)
	{
		return RestAssured.given()
				.spec(requestSpec)
				.body(requestBody)
				.when()
				.post(endpoint)
				.then()
				.extract()
				.response();
	}
	
	
	
}
