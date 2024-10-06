package com.eShopOnWeb.api.authinfo;

import com.eShopOnWeb.api.constants.EndPoints;
import com.eShopOnWeb.api.model.AuthPojo;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

/**
 * Created by Soniya Patel
 */

public class AuthRequest {

    public ValidatableResponse auth(String username, String password) {
        return given()
                .header("accept", "text/plain")
                .header("Content-Type", "application/json")
                .body(AuthPojo.getAuth(username, password))
                .post(EndPoints.AUTHENTICATE)
                .then();
    }
}
