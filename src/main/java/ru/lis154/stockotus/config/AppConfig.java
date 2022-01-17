package ru.lis154.stockotus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Value("${urlForCompany}")
    private String urlForCompany;
    @Value("${urlForShares}")
    private String urlForShares;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10);
    }

    public String getUrlForCompany(){
        return urlForCompany;
    }

    public String getTokenForUrl(){
        return "######";
    }

    public String getUrlForShares(){
        return urlForShares;
    }






}
