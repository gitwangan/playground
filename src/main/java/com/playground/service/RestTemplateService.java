package com.playground.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;
    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getUniversities(List<String> countries, boolean multithreading) throws ExecutionException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        CompletableFuture<String>[] futures = new CompletableFuture[countries.size()];
        int i = 0;
        for (String country : countries) {
            if (!multithreading) {
                sb.append(restTemplate.getForObject("http://universities.hipolabs.com/search?country=" + country, String.class));
                sb.append(",");
            } else {
                futures[i++] = CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://universities.hipolabs.com/search?country=" + country, String.class));
            }
        }
        if (multithreading) {
            CompletableFuture.allOf(futures).join();
            for (CompletableFuture future : futures) {
              sb.append(future.get());
              sb.append(",");
            }
        }
        sb.setCharAt(sb.length() - 1, ']');
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }
}
