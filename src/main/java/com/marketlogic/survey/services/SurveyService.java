package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    public List<Survey> findAll(){
        return repository.findAll();
    }

    public Survey save(Survey survey) {
        return repository.save(survey);
    }
}
