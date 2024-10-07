package org.example.apitests;

import io.restassured.response.Response;

import org.example.api.ApiTest;
import org.example.api.ApiUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CatalogBrandApiTest {
    @Test
    public void testGetCatalogBrands() {
        String token = "your_valid_token";  // Replace with actual token
        Response response = ApiUtils.getCatalogBrands(token);
        ApiTest.logResponse(response);
        List<Map<String, Object>> catalogBrands = response.jsonPath().getList("catalogBrands");
        assertTrue("Catalog brands should not be empty", catalogBrands.size() > 0);
    }
}
