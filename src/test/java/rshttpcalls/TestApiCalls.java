package rshttpcalls;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestApiCalls extends BaseTest  {
	

	private String token;
private String issueID;
	@Test
	public void ALoginUser() {
		
		String requestBody = "{\n" + "\"email\": \"Test@example.com\",\n" + "\"password\": \"Test@123\"\n" + "}";
		Response loginResponse = given()
									.header("Content-Type", "application/json")
									.body(requestBody)
									.when()
									.post("/api/login");
		// .then()
		// .statusCode(200);
		loginResponse.then().statusCode(200);
		 token = loginResponse.jsonPath().getString("token");
		System.out.println(token);
		String userID = loginResponse.jsonPath().getString("user.id");
		System.out.println(userID);
	}

	@Test
	public void submitIssue()
	{
		
		System.out.println("Token to be use" + token);
		String issueBody = "{\n"
				+ "\"title\": \"Broken Road \",\n"
				+ "\"category\": \"Road issue\",\n"
				+ "\"description\": \"Road  is broken\",\n"
				+ "\"location\": \"456 Main Street\",\n"
				+ "\"latitude\": 43.6629,\n"
				+ "\"longitude\": -79.3957,\n"
				+ "\"images\": []\n"
				+ "}";
		Response issueResponse =given()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer "+ token )
				.body(issueBody)
				.when()
				.post("/api/report");
		System.out.println("Status Code: " + issueResponse.getStatusCode());
		System.out.println("Location: " + issueResponse.getHeader("Location"));
		issueResponse.then().statusCode(201);
		String message=issueResponse.jsonPath().get("message");
		System.out.println(message);
	}
	
	@Test
	public void BGetUserIssue()
	{
		
		Response issueResponse =given()
				.header("Authorization", "Bearer "+ token )
				.when()
				.get("/api/issues/user");
		System.out.println("Status Code: " + issueResponse.getStatusCode());
		
		List<String> names = issueResponse.jsonPath().getList("issues._id");

		for (String n : names) {
			issueID = n;
		    System.out.println("id of issues: " +n);
		}
		
	}
	
	@Test
	public void CgetIssueDetail()
	{
		System.out.println("ID of issue:  "+issueID);
		Response issueDetailResponse = given()
				.header("Authorization", "Bearer "+ token)
				.when()
				.get("api/issues/" + issueID);
		System.out.println("Status code of issue detail api:" + issueDetailResponse.getStatusCode() );
		
		System.out.println("Title of Issue: "+ issueDetailResponse.jsonPath().getString("issue.title"));
	}
}
