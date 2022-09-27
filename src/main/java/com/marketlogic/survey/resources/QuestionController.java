package com.marketlogic.survey.resources;

import com.marketlogic.survey.entities.Question;
import com.marketlogic.survey.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<Question> disableQuestion(@PathVariable @Valid Integer id){
        Question question = service.disableQuestion(id);
        return ResponseEntity.ok().body(question);
    }
}