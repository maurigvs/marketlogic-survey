package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Choice;
import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.entities.dto.QuestionRequestDto;
import com.marketlogic.survey.entities.dto.SurveyRequestDto;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import com.marketlogic.survey.repositories.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
class SurveyServiceTest {

    @Mock
    private SurveyRepository repository;

    @InjectMocks
    private SurveyService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        List<Survey> surveys = new ArrayList<>();
        surveys.add(new Survey("Survey 1"));
        surveys.add((new Survey("Survey 2")));
        when(repository.findAll()).thenReturn(surveys);

        List<Survey> saved = service.findAll();
        assertTrue(saved.containsAll(surveys));
    }

    @Test
    void findByIdExisting() {
        Optional<Survey> surveyOptional = Optional.of(new Survey("Survey Title"));
        when(repository.findById(any(Integer.class))).thenReturn(surveyOptional);

        Survey surveyFound = service.findById(1);
        assertEquals(surveyFound, surveyOptional.get());
    }

    @Test
    void findByIdNotFound(){
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            Survey survey = service.findById(3);
        });
        assertInstanceOf(EntityNotFoundException.class, ex);
        assertEquals("Survey not found", ex.getMessage());
    }

    @Test
    void createSurvey() {
        SurveyRequestDto dto = new SurveyRequestDto("Survey Title");
        dto.getQuestions().add(new QuestionRequestDto("Question Title"));
        dto.getQuestions().get(0).getChoices().addAll(Arrays.asList("Choice 1", "Choice 2", "Choice 3"));

        Survey survey = new Survey(dto.getTitle());
        survey.getQuestions().add(new Question(dto.getTitle(), QuestionStatus.ENABLED.getCode(), survey));
        for (String choiceTitle : dto.getQuestions().get(0).getChoices()) {
            Question question = survey.getQuestions().get(0);
            question.getChoices().add(new Choice(choiceTitle, question));
        }
        when(repository.save(any(Survey.class))).thenReturn(survey);

        Survey created = service.createSurvey(dto);
        assertEquals(created, survey);
    }
}