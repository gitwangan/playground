package com.playground.service;

import com.playground.pojo.University;

import java.util.List;

public interface UniversityService {
    List<University> getUniversities(List<String> countries, boolean multithreading);

}
