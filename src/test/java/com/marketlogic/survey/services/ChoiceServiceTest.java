package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Choice;
import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.repositories.ChoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@DataJpaTest
class ChoiceServiceTest {

    @Mock
    private ChoiceRepository repository;

    @InjectMocks
    ChoiceService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createChoices() {
        Question q = new Question();
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(1, "Choice 1", true, q));
        choices.add(new Choice(2, "Choice 2", true, q));
        when(repository.saveAll(anyCollection())).thenReturn(choices);

        List<Choice> saved = service.createChoices(choices);
        assertTrue(saved.containsAll(choices));
    }
}