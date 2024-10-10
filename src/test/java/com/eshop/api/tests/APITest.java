package com.eshop.api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class APITest {
    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;
    APIResponse apiResponse;

    private static String token;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        request = playwright.request();
        requestContext = request.newContext();
    }

    @Test(priority = 1)
    public void getToken() throws IOException {
        Map<String, Object> userData = new HashMap<String, Object>();
        userData.put("username", "admin@microsoft.com");
        userData.put("password", "Pass@word1");
        apiResponse = requestContext.post("http://localhost:5200/api/authenticate",
                RequestOptions.create()
                        .setData(userData));
        System.out.println(apiResponse.status());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonResponse.toPrettyString());
        Assert.assertEquals(jsonResponse.get("result").asText().trim(), "true", "Actual Result is not correct " + jsonResponse.get("result") );
        token = jsonResponse.get("token").asText();
    }

    @Test(priority = 2)
    public void getSpecificCatalogIem() throws IOException {
        apiResponse = requestContext.get("http://localhost:5200/api/catalog-items/2",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", token));
        int statusCode = apiResponse.status();
        System.out.println("api response code is " + statusCode);
        Assert.assertEquals(apiResponse.status(), 200);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonResponse.toPrettyString());
        Assert.assertEquals(jsonResponse.get("catalogItem").get("name").asText(),".NET Black & White Mug", "Incorrect item name");
    }

    @AfterTest
    public void tearDown() {
        playwright.close();
    }
}
