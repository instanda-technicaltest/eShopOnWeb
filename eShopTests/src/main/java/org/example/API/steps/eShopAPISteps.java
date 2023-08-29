package org.example.API.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.example.API.common.eShopAPITests;
import org.json.JSONException;
import org.junit.Assert;

public class eShopAPISteps {

    eShopAPITests apiTests = new eShopAPITests();
    static Response response;

    @Given("User authenticates with {string}, {string}")
    public void userAuthenticatesWith(String userName, String password) throws JSONException {
        apiTests.authenticateUser(userName, password);
    }

    @Given("User sends get request for catalog brands api")
    public void catalogbrandsapi() throws JSONException {
        response = apiTests.catalogBrands();
    }

    @Given("User sends post request for catalog items api")
    public void catalogItemsapi() throws JSONException {
        response = apiTests.catalogItems();
    }

    @Then("User receives {int} status")
    public void userReceivesSuccessResponse(int status) {
        Assert.assertEquals(response.statusCode(), status);
    }


}

