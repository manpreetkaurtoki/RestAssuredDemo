package api.clients;

import io.restassured.response.Response;

public class UserIssueApiClient extends BaseApiClient{
private static final String USERISSUE_ENDPOINT = "/api/issues/user";
	
	public static Response getUserIssueResponse()
	{
		return SendGetRequest(USERISSUE_ENDPOINT);
		
	}

}
