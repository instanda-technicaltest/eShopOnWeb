package org.example.apitests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import org.example.api.ApiTest;
import org.example.api.ApiUtils;
import org.example.api.payload.CatalogItem;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogItemApiTest {
    @BeforeClass
    public static void setup() {
        // Set a default parser to JSON for cases where Content-Type is not present
        RestAssured.defaultParser = Parser.JSON;
    }
    @Test
    public void testGetCatalogItem() {
        // Assume you have already authenticated and have a valid token
        String token = "your_valid_token";  // Replace with actual token
        int catalogItemId = 2;  // Replace with an actual catalog item ID
        Response response = ApiUtils.getCatalogItem(catalogItemId, token);
        ApiTest.logResponse(response);
        CatalogItem catalogItem = response.jsonPath().getObject("catalogItem", CatalogItem.class);
        assertNotNull("Catalog item should not be null", catalogItem);
        assertEquals("Catalog item ID should match", catalogItemId, catalogItem.getId());
        System.out.println("Catalog Item Name: " + catalogItem.getName());
    }
    @Test
    public void testGetInvalidCatalogItem() {
        int invalidCatalogItemId =-13;
        String token="token";
        Response response = ApiUtils.getInvalidCatalogItem(invalidCatalogItemId,token);
        ApiTest.logResponse(response);
        assertEquals(404, response.getStatusCode());
        String errorMessage = response.getBody().asString();
        assertTrue(errorMessage.isEmpty() || errorMessage.contains("Not Found"),
                "Expected an empty body or 'Not Found' message for invalid catalog item");
    }
}







