package com.marketlogic.survey.services;

import com.marketlogic.survey.entities.Choice;
import com.marketlogic.survey.repositories.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChoiceService {

    @Autowired
    private ChoiceRepository repository;

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Choice> createChoices(List<Choice> choices){
        validateCreation(choices);
        return repository.saveAll(choices);
    }

    private void validateCreation(List<Choice> choices) {
        for(Choice c : choices){
            if(c.getTitle().isBlank()){
                throw new IllegalArgumentException("Choice title is missing");
            }
        }
    }
}