package com.example.doodle.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Participant {
    public Long id;
    public String name;
    public List<Long> preferences = new ArrayList<Long>();
}
