package com.magneto.detector.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magneto.detector.entities.DnaRequest;
import com.magneto.detector.services.MutantDetector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MutantDetectorController.class)
public class MutantDetectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MutantDetector mutantDetector;

    @Test
    public void givenRequest_whenMutant_thenReturn200() throws Exception {
        String[] test = new String[]{};

        DnaRequest request = new DnaRequest();
        request.setDna(test);

        when(mutantDetector.isMutant(test)).thenReturn(true);
        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenRequest_whenNotMutant_thenReturn403() throws Exception {
        String[] test = new String[]{};

        DnaRequest request = new DnaRequest();
        request.setDna(test);

        when(mutantDetector.isMutant(test)).thenReturn(false);
        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void givenRequest_whenWrongData_thenReturn400() throws Exception {
        String[] test = new String[]{};

        DnaRequest request = new DnaRequest();
        request.setDna(test);

        when(mutantDetector.isMutant(test)).thenReturn(false);
        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void givenRequest_whenNotBody_thenReturn400() throws Exception {
        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
