package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Choice;
import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.entities.dto.QuestionRequestDto;
import com.marketlogic.survey.entities.dto.SurveyRequestDto;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import com.marketlogic.survey.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
        Optional<Survey> surveyOptional = repository.findById(id);
        return surveyOptional.orElseThrow(() -> new EntityNotFoundException("Survey not found"));
    }

    public Survey createSurvey(SurveyRequestDto dto) {
        Survey survey = parseFromDto(dto);
        return repository.save(survey);
    }

    private Survey parseFromDto(SurveyRequestDto dto) {
        Survey survey = new Survey(dto.getTitle());
        for (QuestionRequestDto q : dto.getQuestions()) {
            Question question = new Question(q.getTitle(), QuestionStatus.ENABLED.getCode(), survey);
            for (String c : q.getChoices()) question.getChoices().add(new Choice(c, question));
            survey.getQuestions().add(question);
        }
        return survey;
    }
}
