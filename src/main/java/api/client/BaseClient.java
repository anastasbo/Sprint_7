package api.client;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    protected RequestSpecification getRequestSpecification(String url, String path)
    {
        return RestAssured.given()
                .baseUri(url)
                .basePath(path)
                .log()
                .all();
    }
}
