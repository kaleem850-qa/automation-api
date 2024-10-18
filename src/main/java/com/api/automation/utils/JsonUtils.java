package com.api.automation.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

    // Validate status from the response JSON
    public static String getStatusFromJson(String responseBody) {
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        return jsonObject.get("status").getAsString();
    }

    // Get data section from JSON
    public static JsonObject getDataFromJson(String responseBody) {
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        return jsonObject.getAsJsonObject("data");
    }

    // Get status from JSON
    public static String getDataStatusFromJson(String responseBody) {
        JsonObject jsonObject = getDataFromJson(responseBody);
        return jsonObject.get("status").getAsString();
    }

    // Validate specific error message
    public static String getErrorMessage(String responseBody) {
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        return jsonObject.get("errorMessage").getAsString();
    }

    // Example method to extract userId
    public static String getUserIdFromJson(String responseBody) {
        return getDataFromJson(responseBody).get("userId").getAsString();
    }
}

