package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Choice;
import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import com.marketlogic.survey.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    public List<Survey> findAll(){
        return repository.findAll();
    }

    public Survey findById(Integer id) {
        return repository.getReferenceById(id);
    }

    public Survey createSurvey(Survey survey) {
        //checkRequiredArgs(survey);
        for (Question q : survey.getQuestions()) {
            q.setSurvey(survey);
            q.setStatus(QuestionStatus.ENABLED.getCode());
            for (Choice c : q.getChoices()) {
                c.setQuestion(q);
            }
        }
        return repository.save(survey);
    }
}
