package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public List<Question> saveAll(List<Question> questions){
        return repository.saveAll(questions);
    }
}
