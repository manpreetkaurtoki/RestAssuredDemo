package rshttpcalls;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.clients.LoginApiClient;
import api.clients.UserIssueApiClient;
import api.model.LoginRequest;
import api.model.LoginResponse;
import api.model.UserIssueResponse;
import api.utils.TokenManager;
import io.restassured.response.Response;

public class TestApiCalls {

	private String token;
	private String issueID;

	// WITH API FRAMEWORK
	@Test
	public void testALogin() {
		LoginRequest loginreq = new LoginRequest("Test@example.com", "Test@123");
		Response response = LoginApiClient.getLoginResponse(loginreq);
		Assert.assertEquals(response.getStatusCode(), 200, "Expected code is 200");
		LoginResponse loginResponse = response.as(LoginResponse.class);
		TokenManager.setToken(loginResponse.getToken());
		System.out.println(TokenManager.getToken());
		System.out.println("Details of user : " + loginResponse.getUser().getName() + "");
		System.out.println("Details of user : " + loginResponse.getUser().getId() + "");
	}

	@Test
	public void testBGetUserIssues() {
	
		Response response = UserIssueApiClient.getUserIssueResponse();
		System.out.println("User ISSUE"+response.getStatusCode());
		
		UserIssueResponse issueResponse = response.as(UserIssueResponse.class);
		System.out.println("Issue Name: " + issueResponse.getIssues().get(0).getTitle());
		//System.out.println("Status Code: " + response.getStatusCode());
	}
}
	// WITHOUT API FRAMEWORK JUST EXTEND BASETEST
/*	@Test
	public void ALoginUser() throws IOException {
		String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/login.json")));

		Response loginResponse = given().header("Content-Type", "application/json").body(requestBody).when()
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
	public void submitIssue() throws IOException {

		System.out.println("Token to be use" + token);
		String issueBody = new String(Files.readAllBytes(Paths.get("src/test/resources/submitIssue.json")));

		Response issueResponse = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token).body(issueBody).when().post("/api/report");
		System.out.println("Status Code: " + issueResponse.getStatusCode());
		System.out.println("Location: " + issueResponse.getHeader("Location"));
		issueResponse.then().statusCode(201);
		String message = issueResponse.jsonPath().get("message");
		System.out.println(message);
	}

	@Test
	public void BGetUserIssue() {

		Response issueResponse = given().header("Authorization", "Bearer " + token).when().get("/api/issues/user");
		System.out.println("Status Code: " + issueResponse.getStatusCode());

		List<String> names = issueResponse.jsonPath().getList("issues._id");

		for (String n : names) {
			issueID = n;
			System.out.println("id of issues: " + n);
		}

	}

	@Test
	public void CgetIssueDetail() {
		System.out.println("ID of issue:  " + issueID);
		Response issueDetailResponse = given().header("Authorization", "Bearer " + token).when()
				.get("api/issues/" + issueID);
		System.out.println("Status code of issue detail api:" + issueDetailResponse.getStatusCode());

		System.out.println("Title of Issue: " + issueDetailResponse.jsonPath().getString("issue.title"));
	}
}*/
