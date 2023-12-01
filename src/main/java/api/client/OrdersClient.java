package api.client;

import api.model.Order;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

final public class OrdersClient extends BaseClient{

    private static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    private static final String PATH_ORDER = "/api/v1/orders";

    @Step("Send POST request to /api/v1/orders")
    public ValidatableResponse getOrderFormResponse(Order order) {
        return getRequestSpecification(BASE_URL, PATH_ORDER)
                .filter(new AllureRestAssured())
                .and()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post()
                .then()
                .log()
                .all();
    }

    @Step("Send GET request to /api/v1/orders")
    public Response getOrderListResponse() {
        return getRequestSpecification(BASE_URL, PATH_ORDER)
                .header("Content-type", "application/json")
                .log()
                .all()
                .get();
    }
}
