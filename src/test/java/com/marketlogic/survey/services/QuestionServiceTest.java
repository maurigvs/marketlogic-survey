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
        Question q1 = new Question(1, "Question 1", new Survey(), QuestionStatus.ENABLED);
        Choice c1 = new Choice(1, "Choice 1", true, q1);
        Choice c2 = new Choice(2, "Choice 2", true, q1);
        q1.getChoices().addAll(Arrays.asList(c1, c2));
        questions.add(q1);

        Question q2 = new Question(2, "Question 2", new Survey(), QuestionStatus.ENABLED);
        Choice c3 = new Choice(3, "Choice 3", true, q2);
        Choice c4 = new Choice(4, "Choice 4", true, q2);
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
        Question question = new Question(1, "Question 1", new Survey(), QuestionStatus.DISABLED);
        when(repository.getReferenceById(any(Integer.class))).thenReturn(question);
        when(repository.save(any(Question.class))).thenReturn(question);

        Question saved = service.disableQuestion(1);
        assertEquals(saved, question);
    }
}