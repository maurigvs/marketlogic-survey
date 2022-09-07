package com.marketlogic.survey.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.services.SurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
class SurveyControllerTest {

    @MockBean
    private SurveyService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getSurveys() throws Exception {
        Survey survey1 = new Survey(null, "Survey Title 1");
        Survey survey2 = new Survey(null, "Survey Title 2");
        List<Survey> surveys = new ArrayList<>(Arrays.asList(survey1, survey2));

        when(service.findAll()).thenReturn(surveys);
        mockMvc.perform(get("/survey")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void postSurvey() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Survey survey = new Survey(null, "Survey Title");

        when(service.createSurvey(any(Survey.class))).thenReturn(survey);
        mockMvc.perform(post("/survey")
                .content(mapper.writeValueAsString(survey))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(survey.getTitle()));
    }
}