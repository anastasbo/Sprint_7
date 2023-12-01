package api.client;

import api.model.Courier;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;

final public class CourierClient extends BaseClient {

    private static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    private static final String PATH_LOGIN = "/api/v1/courier/login";
    private static final String PATH_COURIER = "/api/v1/courier";

    @Step("Send POST request to /api/v1/courier/login")
    public ValidatableResponse getLoginFormResponse(Courier courier) {
        return getRequestSpecification(BASE_URL, PATH_LOGIN)
                .filter(new AllureRestAssured())
                .and()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post()
                .then()
                .log()
                .all();
    }

    @Step("Send POST request to /api/v1/courier")
    public ValidatableResponse getCourierApiV1Response(Courier courier) {
        return getRequestSpecification(BASE_URL, PATH_COURIER)
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post()
                .then()
                .log()
                .all();
    }
}