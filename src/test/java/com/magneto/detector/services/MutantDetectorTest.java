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

    @Test
    public void testIsNotMutant() throws InvalidDnaSequence {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CACCTA","TCACTG"};

        Assertions.assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    public void testInvalidADN() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTGE"};

        Exception exception = Assertions.assertThrows(InvalidDnaSequence.class, () -> mutantDetector.isMutant(dna));

        Assertions.assertEquals(InvalidDnaSequence.INVALID_DNA, exception.getMessage());
    }

    @Test
    public void testInvalidADNSize() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTE"};

        Exception exception = Assertions.assertThrows(InvalidDnaSequence.class, () -> mutantDetector.isMutant(dna));

        Assertions.assertEquals(InvalidDnaSequence.INVALID_DNA, exception.getMessage());
    }

    @Test
    public void testInvalidEmpty() {
        String[] dna = {};

        Exception exception = Assertions.assertThrows(InvalidDnaSequence.class, () -> mutantDetector.isMutant(dna));

        Assertions.assertEquals(InvalidDnaSequence.EMPTY, exception.getMessage());
    }
}
