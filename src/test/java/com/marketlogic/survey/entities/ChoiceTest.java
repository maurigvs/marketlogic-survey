package com.marketlogic.survey.entities;

import com.marketlogic.survey.entities.enums.QuestionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceTest {

    private Choice choice;

    @BeforeEach
    void setUp() {
        choice = new Choice(null, "Choice title", false, new Question());
    }

    @Test
    void getId() {
        assertNull(choice.getId());
    }

    @Test
    void setId() {
        choice.setId(1);
        assertEquals(1, choice.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Choice title", choice.getTitle());
    }

    @Test
    void setTitle() {
        choice.setTitle("Another choice title");
        assertEquals("Another choice title", choice.getTitle());
    }

    @Test
    void isCorrect() {
        assertFalse(choice.isCorrect());
    }

    @Test
    void setCorrect() {
        choice.setCorrect(true);
        assertTrue(choice.isCorrect());
    }

    @Test
    void getQuestion() {
        assertInstanceOf(Question.class, choice.getQuestion());
    }

    @Test
    void setQuestion() {
        Question question = new Question(null, "Question title", new Survey(), QuestionStatus.ENABLED);
        choice.setQuestion(question);
        assertEquals(question, choice.getQuestion());
    }

    @Test
    void testEquals() {
        choice.setId(1);
        Choice otherChoice = new Choice(null, "New choice title", false, new Question());
        otherChoice.setId(1);
        assertEquals(choice, otherChoice);
    }

    @Test
    void testHashCode() {
        choice.setId(1);
        Choice otherChoice = new Choice(null, "New choice title", false, new Question());
        otherChoice.setId(1);
        assertEquals(choice.hashCode(), otherChoice.hashCode());
    }
}