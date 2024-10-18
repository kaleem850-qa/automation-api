package com.api.automation;

import com.api.automation.client.ApiClient;
import com.api.automation.utils.FileReader;
import com.api.automation.utils.JsonUtils;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateUserTests {

    private static final String API_KEY = "GombImxOhMCa8AqMmNM9KEFwaSHSFHty";
    private ApiClient apiClient;
    @BeforeClass
    public void setUp() {
        apiClient = new ApiClient();
    }


    @Test(description = "Test to create a user with valid data")
    public void testCreateUserWithValidData() throws IOException {
        String payload = FileReader.readJsonFile("validUserPayload");

        Response response = apiClient.createUser(payload, API_KEY);
        Assert.assertEquals(response.code(), 200, "Expected HTTP status 201");

        String responseBody = response.body().string();

        // Assert status code is 200
        Assert.assertEquals(response.code(), 200, "Expected HTTP status 200");

        // Assert status field in response
        Assert.assertEquals(JsonUtils.getStatusFromJson(responseBody), "Success", "Expected 'Success' in response");

        // Assert that the userId is returned
        Assert.assertNotNull(JsonUtils.getUserIdFromJson(responseBody), "Expected a userId in response");
    }

    @Test(description = "Test to create a user without title")
    public void testCreateUserWithOutTitle() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithoutTitle");

        Response response = apiClient.createUser(payload, API_KEY);

        String responseBody = response.body().string();

        // Assert status code is 200
        Assert.assertEquals(response.code(), 200, "Expected HTTP status 200");

        // Assert status field in response
        Assert.assertEquals(JsonUtils.getStatusFromJson(responseBody), "Success", "Expected 'Success' in response");
    }

    @Test(description = "Test to create a user with invalid title")
    public void testCreateUserWithInvalidTitle() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithInvalidTitle");

        Response response = apiClient.createUser(payload, API_KEY);

        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error in response
        Assert.assertEquals(JsonUtils.getErrorMessage(responseBody), "Validation error - unknown title", "Expected 'Validation error - unknown title' in response");
    }

    @Test(description = "Test to create a user with missing firstName")
    public void testCreateUserWithMissingFirstName() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithMissingFirstName");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error message
        Assert.assertTrue(JsonUtils.getErrorMessage(responseBody).contains("__ERR_FNAME_INVALID__"), "Expected 'First name is required' error");
    }

    @Test(description = "Test to create a user with firstName with Wrong Length")
    public void testCreateUserWithWrongLengthFirstName() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithWrongLengthFirstName");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error message
        Assert.assertTrue(JsonUtils.getErrorMessage(responseBody).contains("__ERR_FNAME_INVALID__"), "Expected 'First name is required' error");
    }

    @Test(description = "Test to create a user with missing lastName")
    public void testCreateUserWithMissingLastName() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithMissingLastName");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error message
        Assert.assertTrue(JsonUtils.getErrorMessage(responseBody).contains("Lastname is required"), "Expected 'Lastname is required' error");
    }

    @Test(description = "Test to create a user with lastName with Wrong Length")
    public void testCreateUserWithWrongLengthLastName() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithWrongLengthLastName");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error message
        Assert.assertTrue(JsonUtils.getErrorMessage(responseBody).contains("Validation error - last name must be between 2 and 255 characters"), "Expected 'Validation error - last name must be between 2 and 255 characters' error");
    }

    @Test(description = "Test to create a user with invalid email format")
    public void testCreateUserWithInvalidEmail() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithInvalidEmail");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error message
        Assert.assertTrue(JsonUtils.getErrorMessage(responseBody).contains("Validation error - must provide valid e-mail add"), "Expected 'Invalid email format' error");
    }

    @Test(description = "Test to create a user with invalid Date Format")
    public void testCreateUserWithInvalidDateFormat() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithInvalidDateFormat");

        Response response = apiClient.createUser(payload, API_KEY);

        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error in response
        Assert.assertEquals(JsonUtils.getErrorMessage(responseBody), "Validation error - date of birth must be in YYYY-MM-DD format", "Expected 'Validation error - date of birth must be in YYYY-MM-DD format' in response");
    }

    @Test(description = "Test to create a user with invalid rating")
    public void testCreateUserWithInvalidRating() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithInvalidRating");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 400
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error message
        Assert.assertTrue(JsonUtils.getErrorMessage(responseBody).contains("Rating must be between 0 and 10"), "Expected 'Rating must be between 0 and 10' error");
    }

    @Test(description = "Test created user status is rejected for rating 0")
    public void testCreateUserWithZeroRating() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithZeroRating");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 200
        Assert.assertEquals(response.code(), 200, "Expected HTTP status 200");

        // Assert error message
        Assert.assertTrue(JsonUtils.getDataStatusFromJson(responseBody).contains("rejected"), "Expected 'Rating must be between 0 and 10' error");
    }

    @Test(description = "Test created user status is new for rating between 0 and 5")
    public void testCreateUserWithOneRating() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithOneRating");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 200
        Assert.assertEquals(response.code(), 200, "Expected HTTP status 200");

        // Assert error message
        Assert.assertTrue(JsonUtils.getDataStatusFromJson(responseBody).contains("new"), "Expected 'Rating must be between 0 and 10' error");
    }

    @Test(description = "Test created user status is active for rating between 5 and 10")
    public void testCreateUserWithTenRating() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithTenRating");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 200
        Assert.assertEquals(response.code(), 200, "Expected HTTP status 200");

        // Assert error message
        Assert.assertTrue(JsonUtils.getDataStatusFromJson(responseBody).contains("active"), "Expected 'Rating must be between 0 and 10' error");
    }

    @Test(description = "Test to create a user with missing password")
    public void testCreateUserWithMissingPassword() throws IOException {
        String payload = FileReader.readJsonFile("userPayloadWithMissingPassword");

        Response response = apiClient.createUser(payload, API_KEY);
        String responseBody = response.body().string();

        // Assert status code is 200
        Assert.assertEquals(response.code(), 400, "Expected HTTP status 400");

        // Assert error message
        Assert.assertTrue(JsonUtils.getErrorMessage(responseBody).contains("Password is required"), "Expected 'Password is required' error");
    }

    @Test(description = "Test to create a user without Authorization header")
    public void testCreateUserWithoutAuthorization() throws IOException {
        String payload = FileReader.readJsonFile("validUserPayload");

        Response response = apiClient.createUser(payload, "");
        String responseBody = response.body().string();

        // Assert status code is 401
        Assert.assertEquals(response.code(), 401, "Expected HTTP status 401");

    }
}
