package com.marketlogic.survey.resources;

import com.marketlogic.survey.entities.Survey;
import com.marketlogic.survey.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/survey")
public class SurveyController {

    @Autowired
    private SurveyService service;

    @GetMapping
    public ResponseEntity<List<Survey>> getSurveys(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Survey> getById(@PathVariable @Valid Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Survey> postSurvey(@RequestBody @Valid Survey survey){
        survey = service.createSurvey(survey);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(survey.getId()).toUri();
        return ResponseEntity.created(uri).body(survey);
    }
}