package com.example.doodle.api.model;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    public Long id;
    public String name;
    public List<Long> preferences = new ArrayList<Long>();

}