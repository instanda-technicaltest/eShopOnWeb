package org.example.apitests;

import io.restassured.response.Response;

import org.example.api.ApiTest;
import org.example.api.ApiUtils;
import org.example.api.payload.AuthenticationPayload;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuthenticationApiTest {


    @Test
    public void testPostUserAuthentication() {
        // Step 1: Create payload
        AuthenticationPayload authPayload = new AuthenticationPayload("test_user", "test_password");

        // Step 2: Send POST request
        Response response = ApiUtils.authenticateUser(authPayload);

        // Step 3: Log and verify the response
        ApiTest.logResponse(response);

        // Extract token from the response for further tests
        String token = response.path("token");
        assertTrue("Authentication token should not be null", token != null);
    }

//    @Test
//    public void testPostUserAuthenticationWithInvalidCredentials() {
//        AuthenticationPayload authPayload = new AuthenticationPayload("invalid_user", "wrong_password");
//        Response response = ApiUtils.authenticateUser(authPayload);
//        ApiTest.logResponse(response);
//        assertEquals(401, response.getStatusCode());
//
//    }
//    @Test
//    public void testPostUserAuthenticationWithMissingUsername() {
//        AuthenticationPayload authPayload = new AuthenticationPayload(null, "test_password");
//        Response response = ApiUtils.authenticateUser(authPayload);
//        ApiTest.logResponse(response);
//        assertEquals(400, response.getStatusCode());
//
//    }


}
