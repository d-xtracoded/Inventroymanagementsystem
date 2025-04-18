package com.prime.InventroryMgtSystem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
             registry.addMapping("/**")
                     .allowedMethods("GET", "POST", "PUT", "DELETE")
                     .allowedOrigins("*");
            }
        };
    }
}
