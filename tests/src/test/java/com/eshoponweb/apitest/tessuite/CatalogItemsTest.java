package com.eshoponweb.apitest.tessuite;

import com.eshoponweb.api.constants.Endpoints;
import com.eshoponweb.apitest.response.GetResponse;
import com.eshoponweb.apitest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class CatalogItemsTest extends TestBase {
    ValidatableResponse validatableResponse;
    GetResponse getResponse = new GetResponse();
    @Test(description = "Get all catalog items",priority = 1)
    public void getAllCatalogItems() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogItems);
        System.out.println(validatableResponse.extract().body().asString());
    }

    @Test(description = "Get all the items with price less than 10",priority = 2)
    public void getItemsLessThan10() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogItems);
        System.out.println("----------   Verify .NET Foundation Sweatshirt in catalog items---------------");
        List<Double> price =validatableResponse.extract().path("catalogItems.findAll{it.price<10}.name");
        System.out.println(price);

    }
   @Test(description = "Get all items in Tshirt Category ",priority = 3)
    public void getTshirtItems() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogItems);
        System.out.println("----------   Verify T-Shirt is in Catalog---------------");
        List<?> tshirtItems = validatableResponse.extract().path("catalogItems.findAll{it.catalogTypeId==2}");
        System.out.println(tshirtItems);
    }
    @Test(description = "Get all items in Brand 5",priority = 4)
    public void getBrand5Items() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogItems);
        System.out.println("----------   Verify Items in Other Brands is in Catalog ---------------");
        List<?> brand5Items = validatableResponse.extract().path("catalogItems.findAll{it.catalogBrandId==5}");
        System.out.println(brand5Items);
    }
    @Test(description = "Get all Tshirts in .NET Brand",priority = 5)
    public void getTshirtInDotNetBrand() {
        validatableResponse = getResponse.getValidatableResponse(Endpoints.catalogItems);
        System.out.println("----------   Verify T-Shirt in .NET Brand is in Catalog ---------------");
        List<?> tshirtInDotNetBrand = validatableResponse.extract().path("catalogItems.findAll{it.catalogTypeId==2 && it.catalogBrandId==2}.name");
        System.out.println(tshirtInDotNetBrand);
    }

}
