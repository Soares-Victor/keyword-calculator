package com.sellics.keywordcalculator.service;

import com.sellics.keywordcalculator.integration.AmazonIntegration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author victor
 */
@Slf4j
@Service
public class ScoreCalculationService {

    private final AmazonIntegration amazonIntegration;

    @Autowired
    public ScoreCalculationService(AmazonIntegration amazonIntegration) {
        this.amazonIntegration = amazonIntegration;
    }

    /**
     * Return the score in comparison with Amazon API completion
     * @param keyword String
     * @return Mono<Integer>
     */
    public Mono<Integer> calculate(String keyword) {
        return amazonIntegration.get(keyword, keyword).map(response -> {
            List<Integer> scores = new ArrayList<>(Collections.singletonList(0));
            List<Object> objects = new JSONArray(response.toString()).getJSONArray(1).toList();
            objects.forEach(item -> {
                double e = calculateSimilarity(keyword.toLowerCase(), item.toString()) * 100;
                scores.add((int) e);
            });
            log.info(objects.toString());
            return Collections.max(scores);
        });
    }

    /**
     * @param source String
     * @param target String
     * @return double
     */
    public double calculateSimilarity(String source, String target) {
        if (!StringUtils.isEmpty(source) && !StringUtils.isEmpty(target)) {
            if (StringUtils.equals(source, target)) return 1.0D;
            else {
                int stepsToSame = calcLevenshtein(source, target);
                return 1.0D - (double)stepsToSame / (double)Math.max(source.length(), target.length());
            }
        } return 0.0D;
    }

    /**
     * Levenshtein Calculation
     * @param source String
     * @param target String
     * @return int
     */
    private int calcLevenshtein(String source, String target) {
        int sourceCount = source.length();
        int targetCount = target.length();
        if (sourceCount == 0) return targetCount;
        else if (targetCount == 0) return sourceCount;
        else {
            int i = 0;
            int[][] distance = new int[sourceCount + 1][targetCount + 1];
            while (i <= sourceCount) distance[i][0] = i++;
            i = 0;
            while (i <= targetCount) distance[0][i] = i++;
            for(i = 1; i <= sourceCount; ++i) {
                for(int j = 1; j <= targetCount; ++j) {
                    int cost = target.charAt(j - 1) == source.charAt(i - 1) ? 0 : 1;
                    distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1] + cost);
                }
            }
            return distance[sourceCount][targetCount];
        }
    }

}
