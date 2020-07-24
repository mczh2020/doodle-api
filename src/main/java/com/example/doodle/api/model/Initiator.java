package com.example.doodle.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Initiator {
    public String name;
    public String email;
    public Boolean notify;
    public String timeZone;
}