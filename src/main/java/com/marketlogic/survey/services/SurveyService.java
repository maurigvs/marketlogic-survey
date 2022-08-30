package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Survey save(Survey survey) {
        survey = repository.save(survey);
        for (Question question : survey.getQuestions()) {
            question.setSurvey(survey);
        }
        survey.setQuestions(questionService.saveAll(survey.getQuestions()));
        return survey;
    }
}
