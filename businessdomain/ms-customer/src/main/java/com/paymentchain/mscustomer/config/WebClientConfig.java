package com.paymentchain.mscustomer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {


    @Bean
    public WebClient productWebClient(){
        return WebClient
                .builder()
                .baseUrl(MS_PRODUCT_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient transactionWebClient(){
        return WebClient
                .builder()
                .baseUrl(MS_TRANSACTION_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private static final String MS_TRANSACTION_URL = "http://businessdomain-transactions";

    private static final String MS_PRODUCT_URL = "http://businessdomain-product";
}
