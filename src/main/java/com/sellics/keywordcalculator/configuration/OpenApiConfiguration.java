package com.sellics.keywordcalculator.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author victor
 */
@Configuration
public class OpenApiConfiguration {


    /**
     * @see OpenAPI
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Keyword Calculator")
                .version("0.0.1-SNAPSHOT")
                .description("Keyword estimation auto complete Amazon"));
    }

}
