package com.sellics.keywordcalculator.service;

import com.sellics.keywordcalculator.exception.BadRequestException;
import com.sellics.keywordcalculator.model.EstimationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author victor
 */
@Service
public class EstimationService {

    private static final String REGEX = "[^a-zA-Z0-9]";
    private final ScoreCalculationService service;

    @Autowired
    public EstimationService(ScoreCalculationService service) {
        this.service = service;
    }

    /**
     * @param keyword String
     * @return Mono<EstimationResponse>
     * @see EstimationResponse
     */
    public Mono<EstimationResponse> estimate(String keyword) {
        if (isInvalidKeyWord(keyword)) throw new BadRequestException("Invalid Keyword to estimate!");
        return service.calculate(keyword).map(score -> EstimationResponse.builder()
                .keyword(keyword.toLowerCase())
                .score(score)
                .build());
    }

    /**
     * Check if keyword is invalid
     * @param keyword String
     * @return boolean
     */
    private boolean isInvalidKeyWord(String keyword) {
        return  keyword == null ||
                keyword.isEmpty() ||
                keyword.trim().isEmpty() ||
                keyword.trim().replaceAll(REGEX, "").isEmpty();
    }


}
