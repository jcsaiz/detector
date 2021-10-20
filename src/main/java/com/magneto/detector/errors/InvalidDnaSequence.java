package com.magneto.detector.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDnaSequence extends Exception {

    public static final String INVALID_DNA = "Invalid DNA sequence received";
    public static final String EMPTY = "Invalid DNA sequence received";

    public InvalidDnaSequence(String message) {
        super(message);
    }
}
