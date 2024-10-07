package org.example.apitests;

import io.restassured.response.Response;

import org.example.api.ApiTest;
import org.example.api.ApiUtils;
import org.example.api.payload.CatalogType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CatalogTypeApiTest {
    @Test
    public void testGetCatalogTypes() {
        // Assume you have already authenticated and have a valid token
        String token = "your_valid_token";  // Replace with actual token

        // Step 1: Send GET request to fetch catalog types
        Response response = ApiUtils.getCatalogTypes(token);

        // Step 2: Log and verify the response
        ApiTest.logResponse(response);

        // Step 3: Parse the response into a List of CatalogType objects
        List<CatalogType> catalogTypes = response.jsonPath().getList("catalogTypes", CatalogType.class);
        assertTrue("Catalog types should not be empty", catalogTypes.size() > 0);

        // Optionally, log catalog type details
        catalogTypes.forEach(type -> {
            System.out.println("Catalog Type Name: " + type.getName());
        });
    }
}
