package org.example.apitests;

import io.restassured.response.Response;

import org.example.api.ApiTest;
import org.example.api.ApiUtils;
import org.example.api.payload.AuthenticationPayload;
import org.example.api.payload.CatalogItem;
import org.example.api.payload.CreateCatalogItemPayload;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class CreateCatalogItemApiTest {
    private static String token;

    // Step 1: Authenticate and obtain token before any tests are run
    @BeforeClass
    public static void authenticateUser() {
        // Create payload for user authentication
        AuthenticationPayload authPayload = new AuthenticationPayload("admin@microsoft.com", "Pass@word1");

        // Send POST request to authenticate the user
        Response response = ApiUtils.authenticateUser(authPayload);

        // Log the response
        ApiTest.logResponse(response);

        // Extract token from the response
        token = response.path("token");

        // Ensure the token is not null
        assertTrue("Authentication token should not be null", token != null);
    }

    @Test
    public void testPostCatalogItem() {

        // Assume you have already authenticated and have a valid token

        // Step 1: Define the payload for creating a new catalog item
        CreateCatalogItemPayload payload = new CreateCatalogItemPayload(
                1,2,
                "Test Description",  // description
                "Test Name",  // name
                "https://test-picture-uri.com",  // pictureUri
                "base64encodedString",  // pictureBase64
                "testPicture.jpg",  // pictureName
                100.0  // price
        );

        // Step 2: Send POST request to create the catalog item
        Response response = ApiUtils.createCatalogItem(payload, token);

        // Step 3: Handle both 201 Created and 409 Conflict scenarios
        int statusCode = response.getStatusCode();
        if (statusCode == 201) {
            // If 201 (Created), verify the catalog item is created
            CatalogItem catalogItem = response.jsonPath().getObject("catalogItem", CatalogItem.class);
            assertNotNull("Catalog item ID should not be null", catalogItem.getId());
            System.out.println("Catalog Item Name: " + catalogItem.getName());
        } else if (statusCode == 409) {
            // Handle conflict (item already exists)
            System.out.println("Catalog item already exists. Conflict occurred.");
        } else {
            // Unexpected status code, fail the test
            assertNotNull("Unexpected status code: " + statusCode, false);
        }
    }
//    @Test
//    public void testUpdateCatalogItem() {
//        // Define the payload for updating an existing catalog item
//        CreateCatalogItemPayload payload = new CreateCatalogItemPayload(
//
//                1,2,
//                "Test Description",  // description
//                "Test Name",  // name
//                "https://test-picture-uri.com",  // pictureUri
//                "base64encodedString",  // pictureBase64
//                "testPicture.jpg",  // pictureName
//                100.0  // price  // price
//        );
//
//        // Send PUT request to update the catalog item
//        Response response = ApiUtils.updateCatalogItem(payload, token);
//
//        // Log and verify the response
//      //  ApiUtils.logResponse(response);
//
//        // Assert the status code is 200 OK
//        assertEquals(200, response.getStatusCode());
//
//        // Verify the updated catalog item details in the response
//        String name = response.jsonPath().getString("catalogItem.name");
//        String description = response.jsonPath().getString("catalogItem.description");
//
//        assertNotNull("Catalog item name should not be null", name);
//        assertNotNull("Catalog item description should not be null", description);
//
//        // Log the updated name and description
//        System.out.println("Updated Catalog Item Name: " + name);
//        System.out.println("Updated Catalog Item Description: " + description);
//    }
    @Test
    public void testDeleteCatalogItem() {
        // Assume you have a valid token after authentication

        // ID of the catalog item you want to delete
        int catalogItemId = 1;  // Replace with actual catalog item ID

        // Perform DELETE request
        Response response = ApiUtils.deleteCatalogItem(catalogItemId, token);

        // Log the response for debugging purposes
        ApiTest.logResponse(response);

        // Check if the response status code is 200 (indicating success) or as expected
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            // If item successfully deleted
            System.out.println("Catalog item with ID " + catalogItemId + " was deleted successfully.");
        } else if (statusCode == 404) {
            // If item does not exist or already deleted
            System.out.println("Catalog item with ID " + catalogItemId + " was not found (already deleted or never existed).");
            assertEquals("Expected status code 404 when catalog item is not found", 404, statusCode);
        } else {
            // Handle unexpected status codes
            fail("Unexpected status code: " + statusCode);
        }
    }




}
