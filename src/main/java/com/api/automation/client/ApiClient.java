package com.api.automation.client;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ApiClient {

    private static final String BASE_URL = "https://mzo5slmo45.execute-api.eu-west-2.amazonaws.com/v1";
    private static final OkHttpClient client = new OkHttpClient();
    private static final Logger logger = LogManager.getLogger(ApiClient.class);

    // Method to create a user
    public Response createUser(String jsonPayload, String apiKey) throws IOException {
        logger.info("Create User Request to URL: " + BASE_URL);

        RequestBody body = RequestBody.create(jsonPayload, MediaType.get("application/json"));
        Request request;
        if (apiKey.isEmpty()) {
            request = new Request.Builder()
                    .url(BASE_URL + "/users")
                    .post(body)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(BASE_URL + "/users")
                    .post(body)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .build();
        }

        return client.newCall(request).execute();
    }

    // Method to get user by ID
    public Response getUserById(String userId, String apiKey) throws IOException {
        logger.info("Get User Request to URL: " + BASE_URL);

        Request request;
        if (apiKey.isEmpty()) {
            request = new Request.Builder()
                    .url(BASE_URL + "/users/" + userId)
                    .get()
                    .build();
        } else {
            request = new Request.Builder()
                    .url(BASE_URL + "/users/" + userId)
                    .get()
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .build();
        }

        return client.newCall(request).execute();
    }
}
