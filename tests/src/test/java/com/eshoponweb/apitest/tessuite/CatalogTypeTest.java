package com.eshoponweb.apitest.tessuite;

import com.eshoponweb.api.constants.Endpoints;
import com.eshoponweb.apitest.response.GetResponse;
import com.eshoponweb.apitest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class CatalogTypeTest extends TestBase {


    GetResponse getResponse = new GetResponse();
    ValidatableResponse validatableResponse;

    @Test(description = "Get all Catalog Types", priority = 1)
    public void testGetAllCatalogTypes() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogTypesEndpoint);
        System.out.println("----------   Get all Catalog Types---------------");
        System.out.println(validatableResponse.extract().body().asString());
    }

    @Test(description = "Verify Mug is in Catalog", priority = 2)
    public void testVerifyMugInCatalog() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogTypesEndpoint);
        System.out.println("----------   Verify Mug is in Catalog---------------");
        validatableResponse.body("catalogTypes.name", hasItem("Mug"));
    }

    @Test(description = "Verify T-Shirt is in Catalog", priority = 2)
    public void testVerifyTShirtInCatalog() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogTypesEndpoint);
        System.out.println("----------   Verify T-Shirt is in Catalog---------------");
        validatableResponse.body("catalogTypes.name", hasItem("T-Shirt"));
    }

    @Test(description = "Verify Sheet is in Catalog", priority = 2)
    public void testVerifySheetInCatalog() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogTypesEndpoint);
        System.out.println("----------   Verify Sheet is in Catalog---------------");
        validatableResponse.body("catalogTypes.name", hasItem("Sheet"));
    }

    @Test(description = "Verify USB Memory Stick is in Catalog", priority = 2)
    public void testVerifyUSBMemoryStickInCatalog() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogTypesEndpoint);
        System.out.println("----------   Verify USB Memory Stick is in Catalog---------------");
        validatableResponse.body("catalogTypes.name", hasItem("USB Memory Stick"));
    }

    @Test(description = "Total Item Types ", priority = 2)
    public void testTotalItemTypes() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogTypesEndpoint);
        System.out.println("----------   Total Item Types---------------");
        int totalItemTypes = validatableResponse.extract().path("catalogTypes.size()");
        System.out.println("Total Item Types: " + totalItemTypes);
    }
    @Test(description = "Verify Catalog Types has id and name", priority = 2)
    public void testVerifyCatalogTypesHasIdAndName() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogTypesEndpoint);
        System.out.println("----------   Verify Catalog Types has id and name---------------");
        validatableResponse.body("catalogTypes[0]", hasKey("id"));
          validatableResponse.body("catalogTypes[0]", hasKey("name"));
    }

}
