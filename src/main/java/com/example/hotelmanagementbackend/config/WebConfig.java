package com.example.hotelmanagementbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//tells Spring that this is a configuration class
@Configuration
public class WebConfig {

    //registers the object (WebMvcConfigurer) in the Spring context
    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {

            //this method is called by Spring to configure CORS
            //method where I define all the CORS rules.
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //apply this CORS config to all paths
                        .allowedOrigins("http://localhost:4200") //allow requests only from the Angular app
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  //allow only these HTTP methods
                        .allowedHeaders("*")  //allow any headers (Authorization, Content-Type, etc.)
                        .allowCredentials(true);  //allow frontend to send credentials (e.g., cookies, tokens)
            }
        };
    }
}
