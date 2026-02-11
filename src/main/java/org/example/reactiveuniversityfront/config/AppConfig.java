package org.example.reactiveuniversityfront.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
@Configuration
public class AppConfig {
    @Value("${reactiveUrl}")
    private String reactiveUrl;
    @Value("${courseUrl}")
    private String courseUrl;
    @Bean
    public RestClient reactiveUrl() {
        return RestClient.builder().baseUrl(reactiveUrl).build();
    }
    @Bean
    public RestClient courseUrl() {
        return RestClient.builder().baseUrl(courseUrl).build();
    }
}
