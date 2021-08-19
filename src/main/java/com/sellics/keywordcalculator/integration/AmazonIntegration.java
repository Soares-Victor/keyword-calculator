package com.sellics.keywordcalculator.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AmazonIntegration extends Integration{

    private static final String SEARCH_ALIAS = "aps";
    private static final String MKT = "1";

    public AmazonIntegration(@Value("${api.amazon.auto-complete.url}") String baseUrl) {
        super(baseUrl);
    }

    @Override
    public Mono<?> get(String uriParams, Object object) {
        String uri = "?search-alias=".concat(SEARCH_ALIAS).concat("&mkt=").concat(MKT).concat("&q=").concat(uriParams);
        return super.get(uri, object);
    }
}
