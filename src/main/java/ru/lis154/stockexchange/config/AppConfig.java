package ru.lis154.stockexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10);
    }

    public String getUrlForCompany(){
        return "https://sandbox.iexapis.com/stable/ref-data/symbols?token=";
    }

    public String getTokenForUrl(){
        return "Tpk_ee567917a6b640bb8602834c9d30e571";
    }

    public String getUrlForShares(){
        return "https://sandbox.iexapis.com/stable/stock/%s/quote?token=";
    }






}
