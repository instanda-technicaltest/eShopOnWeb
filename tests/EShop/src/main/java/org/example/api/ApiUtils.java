package org.example.api;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.payload.AuthenticationPayload;
import org.example.api.payload.CreateCatalogItemPayload;


import static io.restassured.RestAssured.given;

public class ApiUtils {

    // Base URL for all API requests
    static {
        RestAssured.baseURI = "http://localhost:5200"; // replace with actual base URL
    }

    // Method to perform POST request for authentication
    public static Response authenticateUser(AuthenticationPayload authPayload) {
        return given()
                .header("Content-Type", "application/json")
                .body(authPayload)
                .when()
                .post("/api/authenticate")
                .then()
                .statusCode(200)  // Expect 200 Success
                .extract()
                .response();
    }

    // Method for GET requests (you can extend this further)
    public static Response getUserData(String endpoint, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)  // Example status code check
                .extract()
                .response();
    }
    // Method for GET request to fetch catalog brands
    public static Response getCatalogBrands(String token) {
        return given()
                .header("Authorization", "Bearer " + token)  // Assuming token-based authentication
                .when()
                .get("/api/catalog-brands")
                .then()
                .statusCode(200)  // Expect 200 Success
                .extract()
                .response();
    }
    public static Response getCatalogItem(int catalogItemId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)  // Assuming token-based authentication
                .when()
                .get("/api/catalog-items/" + catalogItemId)
                .then()
                .statusCode(200)  // Expect 200 Success
                .extract()
                .response();
    }
    public static Response getInvalidCatalogItem(int catalogItemId,String token) {
        return given()
                .accept("application/json")
                .when()
                .get("/api/catalog-items/" + catalogItemId)
                .then()
                .extract()
                .response();
    }

    public static Response getCatalogTypes(String token) {
        return given()
                .header("Authorization", "Bearer " + token)  // Assuming token-based authentication
                .when()
                .get("/api/catalog-types")
                .then()
                .statusCode(200)  // Expect 200 Success
                .extract()
                .response();
    }
    // Method for POST request to create a new catalog item
    public static Response createCatalogItem(CreateCatalogItemPayload payload, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/api/catalog-items")
                .then()
                .extract()
                .response();
    }

    public static Response updateCatalogItem(CreateCatalogItemPayload payload, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put("/api/catalog-items")
                .then()
                .extract()
                .response();  // Extract the response and return

    }
    public static Response deleteCatalogItem(int catalogItemId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)  // Pass the bearer token for authentication
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/catalog-items/" + catalogItemId)
                .then()
                .extract()
                .response();

    }
}
