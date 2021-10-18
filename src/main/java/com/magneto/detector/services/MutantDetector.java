package com.magneto.detector.services;

import com.magneto.detector.errors.InvalidDnaSequence;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.magneto.detector.errors.InvalidDnaSequence.EMPTY;
import static com.magneto.detector.errors.InvalidDnaSequence.INVALID_DNA;

@Service
public class MutantDetector {

    private static final Pattern BASES = Pattern.compile("^[ACTG]*$");

    public boolean isMutant(String[] dna) throws InvalidDnaSequence {

        validateDna(dna);

        int seqFound = 0;
        int hFound = 0;
        int vFound = 0;
        int dFound = 0;

        findingLoop:
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                if (hFound == 0) {
                    int hCounter = 0;
                    for (int k = 1; k <= 3; k++) {
                        if (j + k < dna.length && dna[i].charAt(j) == dna[i].charAt(j + k)) {
                            hCounter++;
                        } else {
                            break;
                        }
                    }

                    if (hCounter == 3) {
                        hFound = 1;
                        seqFound++;
                    }
                }

                if (vFound == 0) {
                    int vCounter = 0;
                    for (int k = 1; k <= 3; k++) {
                        if (i+k < dna.length && dna[i].charAt(j) == dna[i+k].charAt(j)) {
                            vCounter++;
                        } else {
                            break;
                        }
                    }

                    if (vCounter == 3) {
                        vFound = 1;
                        seqFound++;
                    }
                }

                if (dFound == 0) {
                    int dCounter = 0;
                    for (int k = 1; k <= 3; k++) {
                        if (i + k < dna.length && j + k < dna.length && dna[i].charAt(j) == dna[i + k].charAt(j + k)) {
                            dCounter++;
                        } else {
                            break;
                        }
                    }

                    if (dCounter == 3) {
                        dFound = 1;
                        seqFound++;
                    }

                    if (dCounter < 3) {
                        int iCounter = 0;
                        for (int k = 1; k <= 3; k++) {
                            if (i - k > 0 && j + k < dna.length && dna[i].charAt(j) == dna[i - k].charAt(j + k)) {
                                iCounter++;
                            } else {
                                break;
                            }
                        }

                        if (iCounter == 3) {
                            dFound = 1;
                            seqFound++;
                        }
                    }
                }

                if (seqFound == 3) {
                    break findingLoop;
                }
            }
        }

        return seqFound == 3;
    }

    private void validateDna(String[] dna) throws InvalidDnaSequence {
        if (dna.length == 0) {
            throw new InvalidDnaSequence(EMPTY);
        }

        boolean isValid = true;
        for (String row : dna) {
            if (row.length() != dna.length) {
                isValid = false;
                break;
            }

            Matcher matcher = BASES.matcher(row);

            if (!matcher.find()) {
                isValid = false;
                break;
            }
        }

        if (!isValid) {
            throw new InvalidDnaSequence(INVALID_DNA);
        }
    }
}
