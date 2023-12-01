package tests.order;

import api.client.OrdersClient;
import api.model.ListOfOrders;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class GetListOfOrdersTest {
    @Test
    @DisplayName("Get list orders.")
    @Description("Get list orders of /api/v1/orders and check status code.")
    public void getListOrdersTest(){
        OrdersClient ordersClient = new OrdersClient();
        Response response = ordersClient.getOrderListResponse();
        response.then().statusCode(200).assertThat();
        ListOfOrders listOfOrders = response.body().as(ListOfOrders.class);
        Assert.assertThat(listOfOrders.getOrders(),Matchers.not(Matchers.empty()));
    }
}