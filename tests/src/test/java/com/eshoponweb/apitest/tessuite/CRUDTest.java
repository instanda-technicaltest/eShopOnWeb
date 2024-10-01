package com.eshoponweb.apitest.tessuite;

import com.eshoponweb.api.constants.Endpoints;
import com.eshoponweb.api.model.AuthPojo;
import com.eshoponweb.api.model.CatalogItemPoJo;
import com.eshoponweb.apitest.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class CRUDTest extends TestBase {

    static String adminUsername = "admin@microsoft.com";
    static String demoUsername = "demouser@microsoft.com";
    static String password = "Pass@word1";
    static String authTokenDemoUser;
    static String authTokenAdminUser;

    static int catalogBrandId=2;
    static int catalogTypeId=3;
    static String description = "Test Description 2";
    static String name = "Test10";
    static double price=88;
   // static String pictureUri="images/products/16.png";
    static int catalogItemId;
    static double priceNew=100;


    @Test (description = "Get token for authentication for Demo User", priority = 1,groups = {"regression"})
    public void getAuthorisationTokenForDemoUser() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(demoUsername);
        authPojo.setPassword(password);
        Response response = given().spec(postLoginRequestSpec())
                .when()
                .body(authPojo)
                .post(Endpoints.authEndpoint);
        response.prettyPrint();
        response.then().statusCode(200);
        authTokenDemoUser = response.then().spec(getResponseSpecification()).extract().path("token");
    }
    @Test (description = "Get token for authentication for Admin User", priority = 1)
    public void getAuthorisationTokenForAdminUser() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(adminUsername);
        authPojo.setPassword(password);
        Response response = given().spec(postLoginRequestSpec())
                .when()
                .body(authPojo)
                .post(Endpoints.authEndpoint);
        response.prettyPrint();
        response.then().statusCode(200);
        authTokenAdminUser = response.then().spec(getResponseSpecification()).extract().path("token");
    }
    @Test (description = "Demo user should not be able to create catalog item", priority = 2)
    public void testCreateCatalogItem() {
        System.out.println("Create Catalog Item for Demo User");
        System.out.println("Token in catalog item method: " + authTokenDemoUser);
        CatalogItemPoJo catalogItemPoJo = new CatalogItemPoJo();
        catalogItemPoJo.setCatalogBrandId(catalogBrandId);
        catalogItemPoJo.setCatalogTypeId(catalogTypeId);
        catalogItemPoJo.setDescription(description);
        catalogItemPoJo.setName(name);
       // catalogItemPoJo.setPictureUri(pictureUri);
        catalogItemPoJo.setPrice(price);

        Response response = given().contentType("application/json")
                .auth().oauth2(authTokenDemoUser)
                .when()
                .body(catalogItemPoJo)
                .post(Endpoints.catalogItems);
        response.prettyPrint();
     response.then().statusCode(403).log().all();
    }
    @Test (description = "Admin user should be able to create catalog item", priority = 3)
    public void testCreateCatalogItemForAdminUser() {
        System.out.println("Create Catalog Item for Admin User");
        System.out.println("Token in catalog item method: " + authTokenAdminUser);
        CatalogItemPoJo catalogItemPoJo = new CatalogItemPoJo();
        catalogItemPoJo.setCatalogBrandId(catalogBrandId);
        catalogItemPoJo.setCatalogTypeId(catalogTypeId);
        catalogItemPoJo.setDescription(description);
        catalogItemPoJo.setName(name);
      //  catalogItemPoJo.setPictureUri(pictureUri);
        catalogItemPoJo.setPrice(price);
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + authTokenAdminUser)
                .when()
                .body(catalogItemPoJo)
                .post(Endpoints.catalogItems);
        response.then().statusCode(201).log().all();
        catalogItemId = (int) response.then().spec(postCreateItemSpecification()).extract().path("catalogItem.id");
        System.out.println("Catalog Item ID: " + catalogItemId);
    }

    @Test (description = "Get Item by ID", priority = 4)
    public void testGetItemById() {
        System.out.println("Get Item by ID "+catalogItemId);
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + authTokenAdminUser)
                .when()
                .get(Endpoints.catalogItems + "/" + catalogItemId);
        response.prettyPrint();
        response.then().statusCode(200).log().all();
    }
    @Test (description = "Update Item price Category", priority = 5)
    public void testUpdateItemById() {
        System.out.println("Update Price of an item "+catalogItemId);
        CatalogItemPoJo catalogItemPoJo = new CatalogItemPoJo();
        catalogItemPoJo.setCatalogBrandId(catalogBrandId);
        catalogItemPoJo.setCatalogTypeId(catalogTypeId);
        catalogItemPoJo.setDescription(description);
        catalogItemPoJo.setName(name);
       // catalogItemPoJo.setPictureUri(pictureUri);
        catalogItemPoJo.setPrice(priceNew);
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + authTokenAdminUser)
                .when()
                .body(catalogItemPoJo)
                .put(Endpoints.catalogItems);
        response.prettyPrint();
        response.then().statusCode(201).log().all();
    }
    @Test (description = "Delete Item by ID", priority = 6)
    public void testDeleteItemById() {
        System.out.println("Delete Item by ID "+catalogItemId);

        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + authTokenAdminUser)
                .when()
                .delete(Endpoints.catalogItems + "/" + catalogItemId);
        response.prettyPrint();
        response.then().statusCode(200).log().all();
        response.then().extract().path("status").equals("Deleted");
    }
}
