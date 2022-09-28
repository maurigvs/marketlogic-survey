package com.marketlogic.survey.entities;

import com.marketlogic.survey.entities.enums.QuestionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceTest {

    private Choice choice;

    @BeforeEach
    void setUp() {
        choice = new Choice("Choice title", new Question());
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
    void getQuestion() {
        assertInstanceOf(Question.class, choice.getQuestion());
    }

    @Test
    void setQuestion() {
        Question question = new Question("Question title", QuestionStatus.ENABLED.getCode(), new Survey());
        choice.setQuestion(question);
        assertEquals(question, choice.getQuestion());
    }

    @Test
    void testConstructorWithoutId(){
        choice = new Choice("New Choice Title", new Question());
        assertNull(choice.getId());
        assertEquals("New Choice Title", choice.getTitle());
        assertNotNull(choice.getQuestion());
    }
}