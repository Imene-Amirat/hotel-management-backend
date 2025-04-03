package com.example.hotelmanagementbackend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration //tells Spring that this is a configuration class
public class WebConfig {

    @Bean //registers this config as a Spring bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            //this method is called by Spring to configure CORS
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**") //apply this CORS config to all paths
                        .allowedOrigins("http://localhost:4200") //allow requests only from the Angular app
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  //allow only these HTTP methods
                        .allowedHeaders("*")  //allow any headers (Authorization, Content-Type, etc.)
                        .allowCredentials(true);  //allow frontend to send credentials (e.g., cookies, tokens)
            }
        };
    }
}
