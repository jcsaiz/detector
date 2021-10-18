package com.magneto.detector.services;

import com.magneto.detector.errors.InvalidDnaSequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MutantDetectorTest {

    @Autowired
    private MutantDetector mutantDetector;

    @Test
    public void testIsMutant() throws InvalidDnaSequence {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        Assertions.assertTrue(mutantDetector.isMutant(dna));
    }
}
