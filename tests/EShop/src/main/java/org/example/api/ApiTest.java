package org.example.api;

import io.restassured.response.Response;

public class ApiTest {

    // Helper method to print and log responses
    public static void logResponse(Response response) {
        System.out.println("Response: " + response.asPrettyString());
    }
}
