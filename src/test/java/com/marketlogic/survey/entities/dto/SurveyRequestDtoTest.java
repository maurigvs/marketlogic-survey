package com.marketlogic.survey.entities.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyRequestDtoTest {

    private SurveyRequestDto survey;

    @BeforeEach
    void setUp() {
        survey = new SurveyRequestDto("Survey Title");
    }

    @Test
    void getTitle() {
        assertNotNull(survey.getTitle());
        assertInstanceOf(String.class, survey.getTitle());
        assertEquals("Survey Title", survey.getTitle());
    }

    @Test
    void getQuestions() {
        assertNotNull(survey.getQuestions());
        assertInstanceOf(List.class, survey.getQuestions());
    }

    @Test
    void setTitle() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}