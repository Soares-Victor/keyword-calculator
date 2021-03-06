package com.sellics.keywordcalculator.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author victor
 */
@Component
@Order(-2)
@Slf4j
public class GlobalErrorWebExceptionHandler extends
        AbstractErrorWebExceptionHandler {

    /**
     * @param errorAttributes ErrorAttributes
     * @param resources WebProperties.Resources
     * @param applicationContext ApplicationContext
     * @param configurer ServerCodecConfigurer
     */
    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageWriters(configurer.getWriters());
    }

    /**
     * @param errorAttributes ErrorAttributes
     * @return RouterFunction<ServerResponse>
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(
            ErrorAttributes errorAttributes) {

        return RouterFunctions.route(
                RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * @param request ServerRequest
     * @return ServerRequest
     */
    private Mono<ServerResponse> renderErrorResponse(
            ServerRequest request) {


        Map<String, Object> errorPropertiesMap = getErrorAttributes(request,
                ErrorAttributeOptions.defaults()
                        .including(ErrorAttributeOptions.Include.MESSAGE));

        int status = 500;

        for (Map.Entry<String, Object> stringObjectEntry : errorPropertiesMap.entrySet()) {
            if (stringObjectEntry.getKey().equals("status")){
                status = Integer.parseInt(stringObjectEntry.getValue().toString());
                break;
            }
        }
        return ServerResponse.status(HttpStatus.valueOf(status))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
    }
}