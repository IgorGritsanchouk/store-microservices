package com.catalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// This configuration is required only for swagger documentation

@Configuration
class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowCredentials(false);
    }
}
