package com.orders.config;

import com.orders.ApplicationProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.json.JsonMapper;

@Configuration
class RabbitMQConfig {
    private final ApplicationProperties properties;

    RabbitMQConfig(ApplicationProperties properties) {

        this.properties = properties;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(properties.orderEventsExchange());
    }

    @Bean
    Queue newOrdersQueue() {
        return QueueBuilder.durable(properties.newOrdersQueue()).build();
    }

    @Bean
    Binding newOrdersQueueBinding() {
        // bind new-order-queue to exchange using routing key as new-order-queue  name
        // we can bind using routing key: "new-orders-key" / but simply use the same queue name as routing key
        return BindingBuilder.bind(newOrdersQueue()).to(exchange()).with(properties.newOrdersQueue());
    }

    @Bean
    Queue deliveredOrdersQueue() {
        return QueueBuilder.durable(properties.deliveredOrdersQueue()).build();
    }

    @Bean
    Binding deliveredOrdersQueueBinding() {
        return BindingBuilder.bind(deliveredOrdersQueue()).to(exchange()).with(properties.deliveredOrdersQueue());
    }

    @Bean
    Queue cancelledOrdersQueue() {
        return QueueBuilder.durable(properties.cancelledOrdersQueue()).build();
    }

    @Bean
    Binding cancelledOrdersQueueBinding() {
        return BindingBuilder.bind(cancelledOrdersQueue()).to(exchange()).with(properties.cancelledOrdersQueue());
    }

    @Bean
    Queue errorOrdersQueue() {
        return QueueBuilder.durable(properties.errorOrdersQueue()).build();
    }

    @Bean
    Binding errorOrdersQueueBinding() {
        return BindingBuilder.bind(errorOrdersQueue()).to(exchange()).with(properties.errorOrdersQueue());
    }

    @Bean
    ApplicationRunner rabbitCheck(AmqpAdmin amqpAdmin) {
        return args -> {
            System.out.println("===== DECLARING TEST EXCHANGE --temp test-- =====");
            amqpAdmin.declareExchange(new DirectExchange("test-orders-exchange"));
        };
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, JsonMapper jsonMapper) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(jsonMapper));
        return rabbitTemplate;
    }

    @Bean
    public JacksonJsonMessageConverter jacksonConverter(JsonMapper mapper) {
        return new JacksonJsonMessageConverter(mapper);
    }

    //    @Bean
    //    ApplicationRunner rabbitCheck(AmqpAdmin amqpAdmin) {
    //        return args -> {
    //            System.out.println("===== RABBITMQ INITIALIZATION =====");
    //
    //            amqpAdmin.initialize();
    //
    //            System.out.println("===== RABBITMQ INITIALIZATION DONE =====");
    //        };
    //    }

    //    @Bean
    //    ApplicationRunner rabbitCheck(AmqpAdmin amqpAdmin) {
    //        return args -> {
    //            System.out.println("===== DECLARING TEST EXCHANGE =====");
    //
    //            amqpAdmin.declareExchange(
    //                    new DirectExchange("test-orders-exchange")
    //            );
    //
    //            System.out.println("===== TEST EXCHANGE DECLARED =====");
    //        };
    //    }

    //    @Bean
    //    ApplicationRunner rabbitCheck(ConnectionFactory connectionFactory, AmqpAdmin amqpAdmin) {
    //
    //        return args -> {
    //            System.out.println("=================================");
    //            System.out.println("ConnectionFactory: " + connectionFactory);
    //            System.out.println("AmqpAdmin: " + amqpAdmin);
    //            System.out.println("=================================");
    //        };
    //    }

}
