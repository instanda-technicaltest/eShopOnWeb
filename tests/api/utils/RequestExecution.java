package api.utils;

import api.datamodel.Auth;
import api.datamodel.NewCatalogItem;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.PropertyReaders;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RequestExecution extends RequestBuilder {

    public static Response getAllCatalogBrands() {
         Response response= given().spec(requestSpecification()).when().get("/catalog-brands");
         return response;
    }

    public static Response getCatalogItemById(String id) {
        int idPassed = Integer.parseInt(id);
        return given().spec(requestSpecification()).when().get("/catalog-items/" + idPassed);
    }

    public static Response acquireToken() throws IOException {
        Auth auth = new Auth();
        auth.setUsername(PropertyReaders.getInstance().readProperty("Email"));
        auth.setPassword(PropertyReaders.getInstance().readProperty("Password"));
        return given().spec(requestSpecification()).when().body(auth).post("/authenticate");
    }

    public static Response newCatalogItemCreation(String id, String token) {
        int idPassed = Integer.parseInt(id);
        NewCatalogItem newItem = new NewCatalogItem(idPassed);

        return given().spec(requestSpecification())
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .body(newItem)
                .when()
                .post("/catalog-items");

    }
}
