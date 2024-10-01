package com.eshoponweb.apitest.response;

import com.eshoponweb.apitest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class GetResponse extends TestBase {

    public ValidatableResponse getValidatableResponse(String path) {
        return given()
                .when().spec(getRequestSpecification())
                .get(path)
                .then().spec(getResponseSpecification());
    }



}
