
package api.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {

    static RequestSpecification req;

    public static RequestSpecification requestSpecification() {
        RestAssured.baseURI = "http://localhost:5200/api";
        req = new RequestSpecBuilder().setBaseUri(RestAssured.baseURI)
                .setContentType(ContentType.JSON).build();
        return req;
    }

}

