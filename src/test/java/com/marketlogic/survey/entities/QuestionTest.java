package com.marketlogic.survey.entities;

import com.marketlogic.survey.entities.enums.QuestionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(null, "Question title", new Survey(), QuestionStatus.ENABLED);
    }

    @Test
    void getId() {
        assertNull(question.getId());
    }

    @Test
    void setId() {
        question.setId(1);
        assertEquals(1, question.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Question title", question.getTitle());
    }

    @Test
    void setTitle() {
        question.setTitle("Another title");
        assertEquals("Another title", question.getTitle());
    }

    @Test
    void getChoices() {
        Choice choice = new Choice(null, "Choice", false, question);
        question.getChoices().add(choice);

        assertInstanceOf(List.class, question.getChoices());
        assertFalse(question.getChoices().isEmpty());
        assertEquals(1, question.getChoices().size());
        assertEquals(choice, question.getChoices().get(0));
    }

    @Test
    void setChoices() {
        Choice choice = new Choice(null, "Choice", false, question);
        List<Choice> choices = new ArrayList<>();
        choices.add(choice);
        question.setChoices(choices);
        assertEquals(choices, question.getChoices());
    }

    @Test
    void getSurvey() {
        assertInstanceOf(Survey.class, question.getSurvey());
    }

    @Test
    void setSurvey() {
        Survey survey = new Survey(null, "Business Survey");
        question.setSurvey(survey);
        assertEquals(survey, question.getSurvey());
    }

    @Test
    void getStatus() {
        assertInstanceOf(QuestionStatus.class, question.getStatus());
    }

    @Test
    void setStatus() {
        question.setStatus(QuestionStatus.DISABLED);
        assertInstanceOf(QuestionStatus.class, question.getStatus());
        assertEquals(QuestionStatus.DISABLED, question.getStatus());
    }

    @Test
    void testEquals() {
        question.setId(2);
        Question otherQuestion = new Question(null, "Question title", new Survey(), QuestionStatus.ENABLED);
        otherQuestion.setId(2);
        assertEquals(question, otherQuestion);
    }

    @Test
    void testHashCode() {
        question.setId(2);
        Question otherQuestion = new Question(null, "Question title", new Survey(), QuestionStatus.ENABLED);
        otherQuestion.setId(2);
        assertEquals(question.hashCode(), otherQuestion.hashCode());
    }
}