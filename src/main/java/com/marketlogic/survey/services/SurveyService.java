package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import com.marketlogic.survey.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    @Autowired
    private QuestionService questionService;

    public List<Survey> findAll(){
        return repository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Survey createSurvey(Survey survey) {
        validateCreation(survey);
        survey = repository.save(survey);
        for (Question question : survey.getQuestions()) {
            question.setSurvey(survey);
            question.setStatus(QuestionStatus.ENABLED);
        }
        survey.setQuestions(questionService.createQuestions(survey.getQuestions()));
        return survey;
    }

    private void validateCreation(Survey survey) {
        if(survey.getQuestions().isEmpty()){
            throw new IllegalArgumentException("Survey must have at least 1 question");
        }
    }
}
