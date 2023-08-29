package org.example.API.common;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class eShopAPITests {

    private static RequestSpecification requestSpecification;

    static String token;
    @Before
    public void setup() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
    }

    public void authenticateUser(String userName, String password) throws JSONException {
        final JSONObject reqJson = new JSONObject();
        reqJson.put("username", userName);
        reqJson.put("password", password);

        ValidatableResponse response = given()
                .spec(requestSpecification)
                .body(reqJson.toString())
                .when().log().all()
                .post(Constants.USERS_ENDPOINT)
                .then().log().all()
                .statusCode(200)
                .body("username", equalTo(userName));

        token = response.extract().body().jsonPath().get("token").toString();
    }

    public Response catalogBrands() {


        Response response = given()
                .spec(requestSpecification)
                .header(Constants.AUTH_HEADER_NAME, "Bearer "+token )
                .when().log().all()
                .get(Constants.CATALOG_BRAND);
        return response;
    }

    public Response catalogItems() {
        String jsonBody = "{\n" +
                "  \"id\": 10000,\n" +
                "  \"catalogBrandId\": 10000,\n" +
                "  \"catalogTypeId\": 10000,\n" +
                "  \"description\": \"string\",\n" +
                "  \"name\": \"string\",\n" +
                "  \"pictureBase64\": \"string\",\n" +
                "  \"pictureUri\": \"string\",\n" +
                "  \"pictureName\": \"string\",\n" +
                "  \"price\": 10000\n" +
                "}";

        Response response = given()
                .spec(requestSpecification)
                .header(Constants.AUTH_HEADER_NAME, "Bearer "+token )
                .body(jsonBody)
                .when().log().all()
                .post(Constants.CATALOG_ITEMS);

        return response;
    }
}
