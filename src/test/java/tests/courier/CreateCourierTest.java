package tests.courier;

import api.client.CourierClient;
import api.model.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierTest {

    private CourierClient clientStep;
    private String login;
    private String password;
    private String firstName;

    @Before
    public void setUp() {
        clientStep = new CourierClient();
        login = RandomStringUtils.randomAlphanumeric(1,10);
        password = RandomStringUtils.randomAlphanumeric(6,8);
        firstName = RandomStringUtils.randomAlphabetic(3,10);
    }

    @After
    public void tearDown() {
        clientStep = null;
        login = null;
        password = null;
        firstName = null;
    }

    @Test
    @DisplayName("Successful create courier.")
    @Description("Check status code and field value for /api/v1/courier(successful request).")
    public void createCourierTest(){
        ValidatableResponse response = clientStep.getCourierApiV1Response(new Courier(login, password, firstName));
        response.statusCode(201).and().assertThat().body("ok", Matchers.is(true));

    }

    @Test
    @DisplayName("Create two identical couriers.")
    @Description("Check status code and message existence when create two identical couriers.")
    public void createTwoIdenticalCouriersTest(){
        Courier courier = new Courier(login, password, firstName);
        clientStep.getCourierApiV1Response(courier);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(409).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create two couriers with identical logins.")
    @Description("Check status code and message existence when create two couriers with identical logins.")
    public void createTwoIdenticalLoginTest(){
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
        Courier courier = new Courier();
        courier.setFirstName(firstName);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without login and first name.")
    @Description("Check status code and message existence in create courier without login and first name(Bad request).")
    public void createCourierWithoutLoginAndFirstName(){
        Courier courier = new Courier();
        courier.setPassword(password);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without password and first name.")
    @Description("Check status code and message existence in create courier without password and first name(Bad request).")
    public void createCourierWithoutPasswordAndFirstName(){
        Courier courier = new Courier();
        courier.setLogin(login);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without password.")
    @Description("Check status code and message existence in create courier without password(Bad request).")
    public void createCourierWithoutPassword(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin(login);
        courier.setFirstName(firstName);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Create courier without login.")
    @Description("Check status code and message existence in create courier without login(Bad request).")
    public void createCourierWithoutLogin(){
        Courier courier = new Courier();
        courier.setFirstName(firstName);
        courier.setPassword(password);
        ValidatableResponse response = clientStep.getCourierApiV1Response(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.notNullValue());
    }
}