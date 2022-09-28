package com.marketlogic.survey.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.entities.dto.QuestionRequestDto;
import com.marketlogic.survey.entities.dto.SurveyRequestDto;
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
        Survey survey1 = new Survey("Survey Title 1");
        Survey survey2 = new Survey( "Survey Title 2");
        List<Survey> surveys = new ArrayList<>(Arrays.asList(survey1, survey2));
        when(service.findAll()).thenReturn(surveys);

        mockMvc.perform(get("/survey")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        Integer id = 1;
        Survey survey = new Survey("Survey Title 1");
        when(service.findById(any(Integer.class))).thenReturn(survey);

        mockMvc.perform(get("/survey/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void postSurvey() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SurveyRequestDto dto = new SurveyRequestDto("Survey Title");
        dto.getQuestions().add(new QuestionRequestDto("Question 1", Arrays.asList("Choice 1", "Choice 2", "Choice 3")));

        Survey survey = new Survey("Survey Title");
        when(service.createSurvey(any(SurveyRequestDto.class))).thenReturn(survey);

        mockMvc.perform(post("/survey")
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(survey.getTitle()));
    }

    @Test
    void postSurveyWithoutTitle() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SurveyRequestDto dto = new SurveyRequestDto();
        dto.getQuestions().add(new QuestionRequestDto("Question 1", Arrays.asList("Choice 1", "Choice 2", "Choice 3")));

        Survey survey = new Survey("Survey Title");
        when(service.createSurvey(any(SurveyRequestDto.class))).thenReturn(survey);

        mockMvc.perform(post("/survey")
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postSurveyWithoutQuestions() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SurveyRequestDto dto = new SurveyRequestDto("Survey Title");

        Survey survey = new Survey("Survey Title");
        when(service.createSurvey(any(SurveyRequestDto.class))).thenReturn(survey);

        mockMvc.perform(post("/survey")
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}