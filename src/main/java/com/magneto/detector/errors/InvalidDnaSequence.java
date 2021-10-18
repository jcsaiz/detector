package com.magneto.detector.errors;

public class InvalidDnaSequence extends Exception {

    public static final String INVALID_DNA = "Invalid DNA sequence received";
    public static final String EMPTY = "Invalid DNA sequence received";

    public InvalidDnaSequence(String message) {
        super(message);
    }
}
