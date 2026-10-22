package com.orders.web.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.orders.AbstractIT;
import com.orders.testdata.TestDataFactory;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.springframework.http.HttpStatus;
// import org.springframework.test.context.jdbc.Sql;

// @Sql("/test-orders.sql")
class OrderControllerTests extends AbstractIT {

    @Nested
    class CreateOrderTests {

        // @Test
        void shouldCreateOrderSuccessfully() {
            // String productCode="P100";
            mockGetProductByCode("P100", "Gone with the Wind.", new BigDecimal("25.50"));

            var payload = """
        {
            "customer" : {
            "name" : "Philip",
                    "email" : "phil@gmail.com",
                    "phone":"0449636508"
        },
            "deliveryAddress" : {
            "addressLine1" : "Market street ",
                    "addressLine2" : "25 flat 7",
                    "city" : "Hailing Iceland",
                    "state" : "Hampshire",
                    "zipCode" : "HR65ZX",
                    "country" : "United Kingdom"
        },
            "items" : [
            {
                "code" : "P100",
                    "name" : "Gone with the Wind.",
                    "price" : 25.50,
                    "quantity" : 1
            }
   ]
        }

        """;
            given().contentType(ContentType.JSON)
                    // .header("Authorization", "Bearer " + getToken())
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    // .get("/api/orders/{productCode}", productCode)
                    .then()
                    .log()
                    .ifValidationFails()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        // @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    // .header("Authorization", "Bearer " + getToken())
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    //    @Nested
    //    class GetOrdersTests {
    //        @Test
    //        void shouldGetOrdersSuccessfully() {
    //            List<OrderSummary> orderSummaries = given().when()
    //                    .header("Authorization", "Bearer " + getToken())
    //                    .get("/api/orders")
    //                    .then()
    //                    .statusCode(200)
    //                    .extract()
    //                    .body()
    //                    .as(new TypeRef<>() {});
    //
    //            assertThat(orderSummaries).hasSize(2);
    //        }
    //    }
    //
    //    @Nested
    //    class GetOrderByOrderNumberTests {
    //        String orderNumber = "order-123";
    //
    //        @Test
    //        void shouldGetOrderSuccessfully() {
    //            given().when()
    //                    .header("Authorization", "Bearer " + getToken())
    //                    .get("/api/orders/{orderNumber}", orderNumber)
    //                    .then()
    //                    .statusCode(200)
    //                    .body("orderNumber", is(orderNumber))
    //                    .body("items.size()", is(2));
    //        }
    //    }
}
