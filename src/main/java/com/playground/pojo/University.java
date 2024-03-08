package com.playground.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class University {
    private String alpha_two_code, name, country;
    @JsonProperty("state-province")
    private String state_province;
    private String[] web_pages, domains;
}
