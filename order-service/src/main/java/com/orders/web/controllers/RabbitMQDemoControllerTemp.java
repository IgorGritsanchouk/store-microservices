package com.orders.web.controllers;

// import com.orders.ApplicationProperties;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

/*    This is temporary solution to verify RabbitMQ inner workings
Validate and test with postman.  Worked OK. Tested. Example below:
POST   http://localhost:8082/send
row  JSON
{
	"routingKey": "delivered-orders",
    "payload": {
        "content": "Order delivered with id=1234"
    }
}
 */

// @RestController
// public class RabbitMQDemoController {
//
//    private final RabbitTemplate rabbitTemplate;
//    private final ApplicationProperties properties;
//
//    public RabbitMQDemoController(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.properties = properties;
//    }
//
//    @PostMapping("/send")
//    public void sendMessage(@RequestBody MyMessage message) {
//        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), message.routingKey(), message.payload());
//    }
//
//    record MyMessage(String routingKey, MyPayload payload) {}
//
//    record MyPayload(String content) {}
// }
