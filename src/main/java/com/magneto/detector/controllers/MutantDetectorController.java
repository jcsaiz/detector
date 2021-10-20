package com.magneto.detector.controllers;

import com.magneto.detector.entities.DnaRequest;
import com.magneto.detector.errors.InvalidDnaSequence;
import com.magneto.detector.services.MutantDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantDetectorController {

    private final MutantDetector mutantDetector;

    @Autowired
    public MutantDetectorController(MutantDetector mutantDetector) {
        this.mutantDetector = mutantDetector;
    }

    @PostMapping(path = "/mutant/", consumes = "application/json", produces = "application/json")
    public ResponseEntity verifyDna(@RequestBody DnaRequest dnaRequest) throws InvalidDnaSequence {
        boolean result = mutantDetector.isMutant(dnaRequest.getDna());

        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
