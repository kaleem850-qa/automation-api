package com.api.automation;

import com.api.automation.client.ApiClient;
import com.api.automation.utils.JsonUtils;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserTests {

    private static final String API_KEY = "GombImxOhMCa8AqMmNM9KEFwaSHSFHty";
    private ApiClient apiClient;
    @BeforeClass
    public void setUp() {
        apiClient = new ApiClient();
    }


    @Test(description = "Test to get user details with valid userId")
    public void testGetUserDetailsWithValidUserId() throws IOException {
        String userId = "79db7309-040e-4ce0-9caa-746936577e9a"; // Replace with a valid user ID

        Response response = apiClient.getUserById(userId, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 200
        Assert.assertEquals(response.code(), 200, "Expected HTTP status 200");

        // Assert status field in response
        Assert.assertEquals(JsonUtils.getStatusFromJson(responseBody), "Success", "Expected 'Success' in response");

        // Assert that the userId matches
        Assert.assertEquals(JsonUtils.getUserIdFromJson(responseBody), userId, "Expected matching userId in response");
    }

    @Test(description = "Test to get user details with invalid userId")
    public void testGetUserDetailsWithInvalidUserId() throws IOException {
        String invalidUserId = "invalidUserId";

        Response response = apiClient.getUserById(invalidUserId, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error message
        Assert.assertTrue(JsonUtils.getErrorMessage(responseBody).contains("User not found"), "Expected 'User not found' error");
    }

    @Test(description = "Test to get user details without Authorization header")
    public void testGetUserDetailsWithoutAuthorization() throws IOException {
        String userId = "79db7309-040e-4ce0-9caa-746936577e9a"; // Replace with a valid user ID

        Response response = apiClient.getUserById(userId, "");
        String responseBody = response.body().string();
        System.out.println(responseBody);

        // Assert status code is 401
        Assert.assertEquals(response.code(), 401, "Expected HTTP status 401");

        // Assert error message
        Assert.assertTrue(responseBody.contains("Unauthorized"), "Expected 'Unauthorized' error");
    }
}
