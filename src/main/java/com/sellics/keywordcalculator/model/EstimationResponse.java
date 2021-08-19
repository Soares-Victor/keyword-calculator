package com.sellics.keywordcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author victor
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstimationResponse implements Serializable {

    private String keyword;
    private Integer score;

}
