package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import com.marketlogic.survey.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public Question disableQuestion(Integer id) {
        Question question = repository.getReferenceById(id);
        question.setStatus(QuestionStatus.DISABLED.getCode());
        return repository.save(question);
    }
}
