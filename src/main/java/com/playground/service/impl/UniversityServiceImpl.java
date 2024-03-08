package com.playground.service.impl;

import com.playground.pojo.University;
import com.playground.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UniversityServiceImpl implements UniversityService {
    private final RestTemplate restTemplate;
    @Value("${hipolabs.url}")
    private String url;

    @Autowired
    public UniversityServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<University> getUniversities(List<String> countries, boolean multithreading) {
        if  (null == countries) {
            countries = new ArrayList<>();
            countries.add("");
        }
        List<University[]> res = new ArrayList<>();
        CompletableFuture[] futures = new CompletableFuture[countries.size()];
        int i = 0;
        for (String country : countries) {
            if (!multithreading) {
                res.add(restTemplate.getForObject(url + (country.length() == 0 ? "" : "?country=" + country), University[].class));
            } else {
                futures[i++] = CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url + (country.length() == 0 ? "" : "?country=" + country), University[].class));
            }
        }
        if (multithreading) {
            CompletableFuture.allOf(futures).join();
            for (CompletableFuture<University[]> future : futures) {
                res.add(future.join());
            }
        }
        return res.stream().flatMap(Stream::of).collect(Collectors.toList());
    }
}
