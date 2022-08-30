package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Choice;
import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private ChoiceService choiceService;

    public List<Question> saveAll(List<Question> questions){
        questions = repository.saveAll(questions);
        for (Question question : questions) {
            for (Choice choice : question.getChoices()) {
                choice.setQuestion(question);
            }
            question.setChoices(choiceService.saveAll(question.getChoices()));
        }
        return questions;
    }
}
