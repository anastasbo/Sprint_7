package tests.order;

import api.client.OrdersClient;
import api.model.Order;
import api.util.Colors;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private Order order;

    public CreateOrderTest(Order order){
        this.order = order;
    }

    @Parameterized.Parameters(name="Тестовые данные: {0}")
    public static Object[][] getOrderParameters(){
        return new Object[][] {
                {new Order("Tony", "Stark", "LA, StarkTown", "2",
                        "8-800-555-35-35", "2020-12-14", "Millioner, filantrop, playboy",
                        List.of(Colors.GRAY.name()), 6)},
                {new Order("Naruto", "Uzumaki", "Konoha, 142 apt.", "4",
                        "+7 800 355 35 35", "2020-06-06",
                        "Saske, come back to Konoha", List.of(Colors.BLACK.name()), 5)},
                {new Order("Tatyana","Manaskina","Moscow","10",
                        "+7-999-999-99-99", "2022-07-25", "I want to make a lot of money",
                        List.of(Colors.GRAY.name(), Colors.BLACK.name()), 2)},
                {new Order("Vladimir", "Vladimirovich", "Sochi", "1",
                        "+7-123-456-78-90", "2014-02-28",
                        "RU-SSI-A!", Collections.emptyList(), 8)}
        };
    }

    @Test
    @DisplayName("Create order test.")
    @Description("Successful creation of an order with different parameters.")
    public void createOrderTest(){
        OrdersClient clientStep = new OrdersClient();
        ValidatableResponse response = clientStep.getOrderFormResponse(order);
        response.statusCode(201).and().assertThat().body("track", Matchers.notNullValue());
    }

}