package com.eshoponweb.apitest.testbase;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.CoreMatchers.*;


/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class TestBase {
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 5200;
        RestAssured.basePath = "/api";
    }

    private static RequestSpecification requestSpecification;
    private static RequestSpecBuilder requestSpecBuilder;
    public static RequestSpecification getRequestSpecification() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(RestAssured.baseURI);
        requestSpecBuilder.setBasePath(RestAssured.basePath);
        requestSpecification = requestSpecBuilder.build();
        return requestSpecification;
    }

    private static ResponseSpecification resSpecification;
    private static ResponseSpecBuilder resSpecBuilder;
    public static ResponseSpecification getResponseSpecification() {
        resSpecBuilder = new ResponseSpecBuilder();
        resSpecBuilder.expectStatusCode(200)
                .log(LogDetail.ALL);
        resSpecification = resSpecBuilder.build();
        return resSpecification;
    }

    public static ResponseSpecification postCreateItemSpecification() {
        resSpecBuilder = new ResponseSpecBuilder();
        resSpecBuilder.expectStatusCode(201)
                .log(LogDetail.ALL);
        resSpecification = resSpecBuilder.build();
        return resSpecification;
    }

    public static RequestSpecification postLoginRequestSpec() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(RestAssured.baseURI);
        requestSpecBuilder.setBasePath(RestAssured.basePath);
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecification = requestSpecBuilder.build();
        return requestSpecification;
    }
    public static ResponseSpecification postLoginResponseSpec() {
        resSpecBuilder = new ResponseSpecBuilder();
        resSpecBuilder.expectStatusCode(200)
                .expectBody(containsString("token"))
                .expectBody(hasItems("result",true))
                .log(LogDetail.ALL);
        resSpecification = resSpecBuilder.build();
        return resSpecification;
    }

}
