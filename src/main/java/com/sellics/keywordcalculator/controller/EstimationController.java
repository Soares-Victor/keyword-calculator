package com.sellics.keywordcalculator.controller;

import com.sellics.keywordcalculator.model.EstimationResponse;
import com.sellics.keywordcalculator.service.EstimationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author victor
 */
@RestController
@RequestMapping("/estimates")
@Tag(name = "Estimation", description = "Estimation Calculator")
public class EstimationController {

    private final EstimationService estimateService;

    @Autowired
    public EstimationController(EstimationService estimateService) {
        this.estimateService = estimateService;
    }

    /**
     * @param keyword String
     * @see Mono<EstimationResponse>
     * @return EstimationResponse
     */
    @GetMapping
    @Operation(summary = "Estimate Keyword", description = "Estimate Keyword Auto Complete Amazon")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public Mono<EstimationResponse> estimate(
            @Schema(description = "Keyword to Estimate", defaultValue = "iphone charger")
            @RequestParam String keyword) {
        return estimateService.estimate(keyword);
    }

}
