package com.eShopOnWeb.steps.api;

import com.eShopOnWeb.api.catalogbrandinfo.CatalogBrandRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Soniya Patel
 */


public class CatalogBrandsSteps {

    private CatalogBrandRequest catalogBrandRequest = new CatalogBrandRequest();
    private ValidatableResponse response;

    @Given("eShop CatalogBrand API is running")
    public void eshopCatalogBrandAPIIsRunning() {
    }

    @When("I send a GET request to the CatalogBrand endpoint")
    public void iSendAGETRequestToTheCatalogBrandEndpoint() {
        response = catalogBrandRequest.getCatalogBrands();
    }

    @Then("I must receive a valid status code {int}")
    public void iMustReceiveAValidStatusCode(int statusCode) {
        response.statusCode(statusCode);
    }

    @And("The response content type should be {string}")
    public void theResponseContentTypeShouldBe(String contentType) {
        response.contentType(contentType);
    }

    @And("The response body should contain the following catalog brands:")
    public void theResponseBodyShouldContainTheFollowingCatalogBrands(List<Map<String, String>> catalogBrands) {

        for (int i = 0; i < catalogBrands.size(); i++) {
            Map<String, String> expectedBrand = catalogBrands.get(i);
            int expectedId = Integer.parseInt(expectedBrand.get("id"));
            String expectedName = expectedBrand.get("name");

            response.body("catalogBrands[" + i + "].id", equalTo(expectedId));
            response.body("catalogBrands[" + i + "].name", equalTo(expectedName));
        }
    }
}
