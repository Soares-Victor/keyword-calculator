package com.sellics.keywordcalculator.integration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author victor
 */
public abstract class Integration {

    private final WebClient webClient;

    public Integration(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * @param uriParams String
     * @param object Object
     * @return Mono<?>
     */
    public Mono<?> get (String uriParams, Object object) {
        return webClient.method(HttpMethod.GET)
                .uri(uriParams)
                .retrieve()
                .bodyToMono(object.getClass());
    }

}