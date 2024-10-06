package com.eShopOnWeb.api.catalogbrandinfo;

import com.eShopOnWeb.api.constants.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

/**
 * Created by Soniya Patel
 */

public class CatalogBrandRequest {

    public ValidatableResponse getCatalogBrands() {
        return RestAssured.given()
                .header("accept", "application/json")
                .get(EndPoints.GET_CATALOG_BRANDS)
                .then();
    }
}
