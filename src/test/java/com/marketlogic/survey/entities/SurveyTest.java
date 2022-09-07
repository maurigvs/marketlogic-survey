package com.marketlogic.survey.entities;

import com.marketlogic.survey.entities.enums.QuestionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyTest {

    private Survey survey;

    @BeforeEach
    void setUp() {
        survey = new Survey(null, "Survey title");
    }

    @Test
    void getId() {
        assertNull(survey.getId());
    }

    @Test
    void setId() {
        survey.setId(1);
        assertEquals(1, survey.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Survey title", survey.getTitle());
    }

    @Test
    void setTitle() {
        survey.setTitle("Second Survey Title");
        assertEquals("Second Survey Title", survey.getTitle());
    }

    @Test
    void getQuestions() {
        Question question = new Question(null, "Question title", survey, QuestionStatus.ENABLED);
        survey.getQuestions().add(question);

        assertInstanceOf(List.class, survey.getQuestions());
        assertFalse(survey.getQuestions().isEmpty());
        assertEquals(1, survey.getQuestions().size());
        assertEquals(question, survey.getQuestions().get(0));
    }

    @Test
    void setQuestions() {
        Question question = new Question(null, "Question title", survey, QuestionStatus.ENABLED);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        survey.setQuestions(questions);
        assertEquals(questions, survey.getQuestions());
    }

    @Test
    void testEquals() {
        survey.setId(2);
        Survey otherSurvey = new Survey(null, "Secondary Survey");
        otherSurvey.setId(2);
        assertEquals(survey, otherSurvey);
    }

    @Test
    void testHashCode() {
        survey.setId(2);
        Survey otherSurvey = new Survey(null, "Secondary Survey");
        otherSurvey.setId(2);
        assertEquals(survey.hashCode(), otherSurvey.hashCode());
    }
}