package com.eshoponweb.apitest.tessuite;

import com.eshoponweb.api.model.AuthPojo;
import com.eshoponweb.apitest.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class authEndpoints extends TestBase {
    static String username = "demouser@microsoft.com";
    static String password = "Pass@word1";
    static String authToken;

    @Test(description = "Authenticate User", priority = 1,groups = {"regression"})
    public void testAuthenticateUser() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        Response response = given().spec(postLoginRequestSpec())
                .when()
                .body(authPojo)
                .post("/authenticate");
        response.prettyPrint();
        response.then().statusCode(200);
        authToken = response.then().spec(getResponseSpecification()).extract().path("token");
        System.out.println("Token: " + authToken);
    }

}
