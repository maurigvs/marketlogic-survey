package com.marketlogic.survey.resources;

import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/survey")
public class SurveyController {

    @Autowired
    private SurveyService service;

    @GetMapping
    public ResponseEntity<List<Survey>> getSurveys(){
        List<Survey> surveys = service.findAll();
        return ResponseEntity.ok().body(surveys);
    }

    @PostMapping
    public ResponseEntity<Survey> postSurvey(@RequestBody Survey survey){
        survey = service.save(survey);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(survey.getId()).toUri();
        return ResponseEntity.created(uri).body(survey);
    }
}