package tests.courier;

import api.client.CourierClient;
import api.model.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreateCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Successful create courier.")
    @Description("Check status code and field value for /api/v1/courier(successful request).")
    public void createCourierTest(){
        CourierClient clientStep = new CourierClient();
        String login = RandomStringUtils.randomAlphanumeric(1,10);
        String password = RandomStringUtils.randomAlphanumeric(6,8);
        String firstName = RandomStringUtils.randomAlphabetic(3,10);
        Courier courier = new Courier(login, password, firstName);
        Response response = clientStep.sendPostRequestApiV1Courier(courier);
        response.then().log().all()
                .assertThat().body("ok", Matchers.is(true)).and().statusCode(201);

    }

    @Test
    @DisplayName("Create two identical couriers.")
    @Description("Check status code and message existence when create two identical couriers.")
    public void createTwoIdenticalCouriersTest(){
        CourierClient clientStep = new CourierClient();
        String login = RandomStringUtils.randomAlphanumeric(1,10);
        String password = RandomStringUtils.randomAlphanumeric(6,8);
        String firstName = RandomStringUtils.randomAlphabetic(3,10);
        Courier courier = new Courier(login, password, firstName);
        clientStep.sendPostRequestApiV1Courier(courier);
        Response response = clientStep.sendPostRequestApiV1Courier(courier);
        response.then().log().all()
                .assertThat().body("message", Matchers.notNullValue()).and().statusCode(409);
    }

    @Test
    @DisplayName("Create two couriers with identical logins.")
    @Description("Check status code and message existence when create two couriers with identical logins.")
    public void createTwoIdenticalLoginTest(){
        CourierClient clientStep = new CourierClient();
        String login = RandomStringUtils.randomAlphanumeric(1,10);
        String password = RandomStringUtils.randomAlphanumeric(6,8);
        String firstName = RandomStringUtils.randomAlphabetic(3,10);
        Courier courier = new Courier(login, password, firstName);
        given().log().all()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier");
        courier.setPassword("abracadabra");
        Response response = clientStep.sendPostRequestApiV1Courier(courier);
        response.then().log().all()
                .assertThat().body("message", Matchers.notNullValue()).and().statusCode(409);
    }

    @Test
    @DisplayName("Create courier without login and password.")
    @Description("Check status code and message existence when create courier without login and password(Bad request).")
    public void createCourierWithoutLoginAndPassword(){
        CourierClient clientStep = new CourierClient();
        String firstName = RandomStringUtils.randomAlphabetic(3,10);
        Courier courier = new Courier();
        courier.setFirstName(firstName);
        Response response = clientStep.sendPostRequestApiV1Courier(courier);
        response.then().log().all()
                .assertThat().body("message", Matchers.notNullValue()).and().statusCode(400);
    }

    @Test
    @DisplayName("Create courier without login and first name.")
    @Description("Check status code and message existence in create courier without login and first name(Bad request).")
    public void createCourierWithoutLoginAndFirstName(){
        CourierClient clientStep = new CourierClient();
        String password = RandomStringUtils.randomAlphabetic(6,8);
        Courier courier = new Courier();
        courier.setPassword(password);
        Response response = clientStep.sendPostRequestApiV1Courier(courier);
        response.then().log().all()
                .assertThat().body("message", Matchers.notNullValue()).and().statusCode(400);
    }

    @Test
    @DisplayName("Create courier without password and first name.")
    @Description("Check status code and message existence in create courier without password and first name(Bad request).")
    public void createCourierWithoutPasswordAndFirstName(){
        CourierClient clientStep = new CourierClient();
        String login = RandomStringUtils.randomAlphabetic(3,10);
        Courier courier = new Courier();
        courier.setLogin(login);
        Response response = clientStep.sendPostRequestApiV1Courier(courier);
        response.then().log().all()
                .assertThat().body("message", Matchers.notNullValue()).and().statusCode(400);
    }

    @Test
    @DisplayName("Create courier without password.")
    @Description("Check status code and message existence in create courier without password(Bad request).")
    public void createCourierWithoutPassword(){
        CourierClient clientStep = new CourierClient();
        String login = RandomStringUtils.randomAlphabetic(1,10);
        String firstName = RandomStringUtils.randomAlphabetic(3,10);
        Courier courier = new Courier();
        courier.setLogin(login);
        courier.setFirstName(firstName);
        Response response = clientStep.sendPostRequestApiV1Courier(courier);
        response.then().log().all()
                .assertThat().body("message", Matchers.notNullValue()).and().statusCode(400);
    }

    @Test
    @DisplayName("Create courier without login.")
    @Description("Check status code and message existence in create courier without login(Bad request).")
    public void createCourierWithoutLogin(){
        CourierClient clientStep = new CourierClient();
        String firstName = RandomStringUtils.randomAlphabetic(3,10);
        String password = RandomStringUtils.randomAlphanumeric(6,8);
        Courier courier = new Courier();
        courier.setFirstName(firstName);
        courier.setPassword(password);
        Response response = clientStep.sendPostRequestApiV1Courier(courier);
        response.then().log().all()
                .assertThat().body("message", Matchers.notNullValue()).and().statusCode(400);
    }

}