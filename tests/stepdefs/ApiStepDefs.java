package stepdefs;

import api.datamodel.AllCatalogBrands;
import api.datamodel.CatalogItemModel;
import api.utils.RequestBuilder;
import api.utils.RequestExecution;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.IOException;

public class ApiStepDefs extends RequestBuilder {

    private TestContext testContext;
    ResponseSpecification responseSpec;
    Response response;

    public ApiStepDefs() {
        this.testContext = new TestContext();
    }

    @When("user execute getCatalogBrands")
    public void user_execute_get_catalog_brands() {
        response= RequestExecution.getAllCatalogBrands();
        AllCatalogBrands catalogBrandsData = response.as(AllCatalogBrands.class);
        testContext.setResponse(response);
    }
    @Then("the response code is {string}")
    public void the_response_code_is(String code) {
        Assert.assertEquals(Integer.parseInt(code),testContext.getResponse().statusCode());
    }


    @When("user execute getCatalogItem by id {string}")
    public void userExecuteGetCatalogItemById(String id) throws JsonProcessingException {
        response = RequestExecution.getCatalogItemById(id);
        testContext.setResponse(response);
        ObjectMapper objectMapper = new ObjectMapper();
        CatalogItemModel catalogItemModel = objectMapper.readValue(response.getBody().asString(), CatalogItemModel.class);

    }

    @Given("user acquire the token")
    public void userAcquireTheToken() throws IOException {
        response = RequestExecution.acquireToken();
        testContext.setResponse(response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody().asString());
        String token = jsonNode.get("token").asText();
        testContext.setToken(token);

    }

    @When("user execute postCatalogItem with id {string}")
    public void userExecutePostCatalogItemWithId(String id) {
        response= RequestExecution.newCatalogItemCreation(id, testContext.getToken());
        testContext.setResponse(response);
    }
}
