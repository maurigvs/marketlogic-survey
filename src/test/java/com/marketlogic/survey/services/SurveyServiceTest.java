package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Choice;
import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import com.marketlogic.survey.repositories.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
class SurveyServiceTest {

    @Mock
    private SurveyRepository repository;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private SurveyService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        List<Survey> surveys = new ArrayList<>();
        surveys.add(new Survey(null, "Survey 1"));
        surveys.add((new Survey(null, "Survey 2")));
        when(repository.findAll()).thenReturn(surveys);

        List<Survey> saved = service.findAll();
        assertTrue(saved.containsAll(surveys));
    }

    @Test
    void createSurvey() {
        Survey survey = new Survey(1, "Test Survey");
        Question q1 = new Question(1, "Question 1", survey, QuestionStatus.ENABLED);
        Choice c1 = new Choice(1, "Choice 1", true, q1);
        Choice c2 = new Choice(2, "Choice 2", true, q1);
        q1.getChoices().addAll(Arrays.asList(c1, c2));
        survey.getQuestions().add(q1);
        when(repository.save(any(Survey.class))).thenReturn(survey);

        Survey created = service.createSurvey(survey);
        assertThat(created.getTitle()).isSameAs(survey.getTitle());
    }

    @Test()
    void createSurveyWithoutTitle(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Survey survey = new Survey(null, "");
            survey = service.createSurvey(survey);
        });
        assertEquals("Survey title is missing", ex.getMessage());
    }

    @Test()
    void createSurveyWithoutQuestions(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Survey survey = new Survey(null, "Test Survey");
            survey = service.createSurvey(survey);
        });
        assertEquals("Survey must have at least 1 question", ex.getMessage());
    }
}