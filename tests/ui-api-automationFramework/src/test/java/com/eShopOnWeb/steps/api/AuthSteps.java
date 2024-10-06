package com.eShopOnWeb.steps.api;

import com.eShopOnWeb.api.authinfo.AuthRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

/**
 * Created by Soniya Patel
 */
public class AuthSteps {

    private ValidatableResponse response;

    @Given("authenticate api is running")
    public void authenticateApiIsRunning() {

    }

    @When("I send a POST request to with username {string} and password {string}")
    public void iSendAPOSTRequestToWithUsernameAndPassword(String username, String password) {
        response = new AuthRequest().auth(username, password);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        response.statusCode(statusCode);
    }

    @And("the response result should be true")
    public void theResponseResultShouldBeTrue() {
        response.body("result", equalTo(true));
    }

    @And("the response should contain a valid token")
    public void theResponseShouldContainAValidToken() {
        response.body("token", not(emptyString()));
    }

    @And("the response result should be false")
    public void theResponseResultShouldBeFalse() {
        response.body("result", equalTo(false));
    }

    @And("the response should contain empty token")
    public void theResponseShouldContainEmptyToken() {
        response.body("token", emptyString());
    }

}
