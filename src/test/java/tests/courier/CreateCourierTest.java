package tests.courier;

import api.client.CourierClient;
import api.model.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

public class CreateCourierTest {

    @Test
    @DisplayName("Successful create courier.")
    @Description("Check status code and field value for /api/v1/courier(successful request).")
    public void createCourierTest(){
        CourierClient clientStep = new CourierClient();
        String login = RandomStringUtils.randomAlphanumeric(1,10);
        String password = RandomStringUtils.randomAlphanumeric(6,8);
        String firstName = RandomStringUtils.randomAlphabetic(3,10);
        ValidatableResponse response = clientStep.getCourierApiV1Response(new Courier(login, password, firstName));
        response.statusCode(201).and().assertThat().body("ok", Matchers.is(true));

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
        clientStep.getCourierApiV1Response(courier);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(409).and().assertThat().body("message", Matchers.notNullValue());
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
        clientStep.getCourierApiV1Response(courier);
        courier.setPassword("abracadabra");
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(409).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without login and password.")
    @Description("Check status code and message existence when create courier without login and password(Bad request).")
    public void createCourierWithoutLoginAndPassword(){
        CourierClient clientStep = new CourierClient();
        String firstName = RandomStringUtils.randomAlphabetic(3,10);
        Courier courier = new Courier();
        courier.setFirstName(firstName);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without login and first name.")
    @Description("Check status code and message existence in create courier without login and first name(Bad request).")
    public void createCourierWithoutLoginAndFirstName(){
        CourierClient clientStep = new CourierClient();
        String password = RandomStringUtils.randomAlphabetic(6,8);
        Courier courier = new Courier();
        courier.setPassword(password);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without password and first name.")
    @Description("Check status code and message existence in create courier without password and first name(Bad request).")
    public void createCourierWithoutPasswordAndFirstName(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(3,10));
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without password.")
    @Description("Check status code and message existence in create courier without password(Bad request).")
    public void createCourierWithoutPassword(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(1,10));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(3,10));
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without login.")
    @Description("Check status code and message existence in create courier without login(Bad request).")
    public void createCourierWithoutLogin(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setFirstName(RandomStringUtils.randomAlphabetic(3,10));
        courier.setPassword(RandomStringUtils.randomAlphanumeric(6,8));
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }
}