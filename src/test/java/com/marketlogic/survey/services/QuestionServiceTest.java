package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Choice;
import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import com.marketlogic.survey.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@DataJpaTest
class QuestionServiceTest {

    @Mock
    private QuestionRepository repository;

    @Mock
    private ChoiceService choiceService;

    @InjectMocks
    private QuestionService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createQuestions() {
        List<Question> questions = new ArrayList<>();
        Question q1 = new Question( "Question 1", QuestionStatus.ENABLED.getCode(), new Survey());
        Choice c1 = new Choice("Choice 1", q1);
        Choice c2 = new Choice("Choice 2", q1);
        q1.getChoices().addAll(Arrays.asList(c1, c2));
        questions.add(q1);

        Question q2 = new Question("Question 2", QuestionStatus.ENABLED.getCode(), new Survey());
        Choice c3 = new Choice("Choice 3",q2);
        Choice c4 = new Choice("Choice 4",q2);
        q2.getChoices().addAll(Arrays.asList(c3, c4));
        questions.add(q2);
        when(repository.saveAll(anyList())).thenReturn(questions);

        List<Question> founded = service.createQuestions(questions);
        assertFalse(founded.isEmpty());
        assertEquals(2, founded.size());
        assertTrue(founded.containsAll(questions));
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