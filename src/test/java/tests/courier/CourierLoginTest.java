package tests.courier;

import api.client.CourierClient;
import api.model.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Test;

public class CourierLoginTest {

    @Test
    @DisplayName("Courier enters the system.")
    @Description("Check status code when the courier enters the system(successful request).")
    public void authorizationTest(){
        CourierClient clientStep = new CourierClient();
        ValidatableResponse response = clientStep.getLoginFormResponse(new Courier("ninja1453", "1234"));
        response.statusCode(200).and().assertThat().body("id", Matchers.notNullValue());

    }

    @Test
    @DisplayName("Courier enters the system without login.")
    @Description("Check status code when the courier enters the system without login (bad request).")
    public void authorizationWithoutLoginTest(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setPassword("1234");
        ValidatableResponse response = clientStep.getLoginFormResponse(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.is("Недостаточно данных для входа"));

    }

    /**
     Тест падает по причине дефекта в сервисе Авторизации
     **/
    @Test
    @DisplayName("Courier enters the system without password.")
    @Description("Check status code when the courier enters the system without password (bad request).")
    public void authorizationWithoutPasswordTest(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin("ninja1453");
        ValidatableResponse response = clientStep.getLoginFormResponse(courier);
        response.statusCode(400).and().assertThat().body("message", Matchers.is("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Courier enters the system with wrong password.")
    @Description("Check status code when the courier enters the system with wrong password.")
    public void authorizationWithWrongPasswordTest(){
        CourierClient clientStep = new CourierClient();
        ValidatableResponse response = clientStep.getLoginFormResponse(new Courier("ninja1453", "1234000"));
        response.statusCode(404).and().assertThat().body("message", Matchers.is("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Courier enters the system with wrong login.")
    @Description("Check status code when the courier enters the system with wrong login.")
    public void authorizationWithWrongLoginTest(){
        CourierClient clientStep = new CourierClient();
        ValidatableResponse response = clientStep.getLoginFormResponse(new Courier("ninja1453NarutoILoveYou", "1234"));
        response.statusCode(404).and().assertThat().body("message", Matchers.is("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Courier enters the system with wrong login and password.")
    @Description("Check status code when the courier enters the system with wrong login and password.")
    public void authorizationWithWrongLoginAndPasswordTest(){
        CourierClient clientStep = new CourierClient();
        ValidatableResponse response = clientStep.getLoginFormResponse(new Courier("TakogoLoginaNeSushestvuet", "TakogoParolaNet"));
        response.statusCode(404).and().assertThat().body("message", Matchers.is("Учетная запись не найдена"));
    }
}