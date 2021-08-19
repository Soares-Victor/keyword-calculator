package com.sellics.keywordcalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author victor
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    /**
     * @param message String
     * @see BadRequestException
     */
    public BadRequestException(String message) {
        super(message);
    }

}
