package api.clients;

import api.model.LoginRequest;
import io.restassured.response.Response;

public class LoginApiClient extends BaseApiClient {

	private static final String LOGIN_ENDPOINT = "/api/login";
	
	public static Response getLoginResponse(LoginRequest loginRequest)
	{
		return SendPostRequest(LOGIN_ENDPOINT, loginRequest);
		
	}
}
