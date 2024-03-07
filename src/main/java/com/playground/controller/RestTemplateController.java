package com.playground.controller;

import com.playground.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

// example usage: /universities?multithreading=true
//                request body: ["United States", "China", "Mexico"]
@RestController
public class RestTemplateController {
    private final RestTemplateService restTemplateService;

    @Autowired
    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/universities")
    public ResponseEntity<String> getUniversities(@RequestBody List<String> countries, @RequestParam boolean multithreading) throws ExecutionException, InterruptedException {
        return restTemplateService.getUniversities(countries, multithreading);
    }
}
