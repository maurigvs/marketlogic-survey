package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import com.marketlogic.survey.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
class QuestionServiceTest {

    @Mock
    private QuestionRepository repository;

    @InjectMocks
    private QuestionService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void disableQuestion() {
        Question question = new Question("Question 1", QuestionStatus.DISABLED.getCode(), new Survey());
        when(repository.getReferenceById(any(Integer.class))).thenReturn(question);
        when(repository.save(any(Question.class))).thenReturn(question);

        Question saved = service.disableQuestion(1);
        assertEquals(saved, question);
    }
}