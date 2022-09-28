package com.marketlogic.survey.entities.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionRequestDtoTest {

    private QuestionRequestDto question;

    @BeforeEach
    void setUp() {
        question = new QuestionRequestDto("Question Title");
    }

    @Test
    void getTitle() {
        assertNotNull(question.getTitle());
        assertInstanceOf(String.class, question.getTitle());
        assertEquals("Question Title", question.getTitle());
    }

    @Test
    void getChoices() {
        assertNotNull(question.getChoices());
        assertInstanceOf(List.class, question.getChoices());
    }

    @Test
    void setTitle() {
        question.setTitle("New Question Title");
        assertNotNull(question.getTitle());
        assertInstanceOf(String.class, question.getTitle());
        assertEquals("New Question Title", question.getTitle());
    }

    @Test
    void testEquals() {
        QuestionRequestDto anotherQuestion = new QuestionRequestDto("Question Title");
        assertEquals(question, anotherQuestion);
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