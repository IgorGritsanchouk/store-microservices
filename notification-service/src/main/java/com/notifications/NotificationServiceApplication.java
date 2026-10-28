package com.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// @EnableConfigurationProperties(ApplicationProperties.class)
@ConfigurationPropertiesScan
public class NotificationServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
