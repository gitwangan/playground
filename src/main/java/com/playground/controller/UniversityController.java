package com.playground.controller;

import com.playground.pojo.University;
import com.playground.service.impl.UniversityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// example usage:
//  query all countries : localhost:8080/universities
//  query several countries w/ or w/o multithreading: localhost:8080/universities?countries=United+States,China,Japan,&multithreading=true
@RestController
public class UniversityController {
    private final UniversityServiceImpl restTemplateService;

    @Autowired
    public UniversityController(UniversityServiceImpl restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/universities")
    public ResponseEntity<List<University>> getUniversities(@RequestParam(required = false) List<String> countries, @RequestParam(required = false) boolean multithreading) {
        return new ResponseEntity<>(restTemplateService.getUniversities(countries, multithreading), HttpStatus.OK);
    }
}
