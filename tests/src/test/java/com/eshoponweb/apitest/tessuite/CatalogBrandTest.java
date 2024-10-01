package com.eshoponweb.apitest.tessuite;

import com.eshoponweb.api.constants.Endpoints;
import com.eshoponweb.apitest.response.GetResponse;
import com.eshoponweb.apitest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class CatalogBrandTest extends TestBase {
    GetResponse getResponse = new GetResponse();
    ValidatableResponse validatableResponse;
    @Test(description = "Get all Catalog Brands", priority = 1)
    public void testGetAllCatalogBrands() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogBrands);
        System.out.println("----------   Get all Catalog Brands---------------");
        System.out.println(validatableResponse.extract().body().asString());
    }
    @Test(description = "Get Catalog Brand - Azure", priority = 2)
    public void testGetCatalogBrandAzure() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogBrands);
        System.out.println("----------   Get Catalog Brand - Azure---------------");
        validatableResponse.body("catalogBrands.name", hasItem("Azure"));
    }

    @Test(description = "Get Catalog Brand - .NET", priority = 2)
    public void testGetCatalogBrandDotNet() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogBrands );
        System.out.println("----------   Get Catalog Brand - .NET---------------");
        validatableResponse.body("catalogBrands.name", hasItem(".NET"));
    }

    @Test(description = "Get Catalog Brand - Other", priority = 2)
    public void testGetCatalogBrandOther() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogBrands);
        System.out.println("----------   Get Catalog Brand - Other---------------");
        validatableResponse.body("catalogBrands.name", hasItem("Other"));
    }

    @Test(description = "Verify Total Number of  Brands", priority = 2)
    public void testTotalBrands() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogBrands);
        System.out.println("----------   Total Brands---------------");
        int totalBrands = validatableResponse.extract().path("catalogBrands.size()");
        System.out.println("Total Brands: " + totalBrands);
    }

}
