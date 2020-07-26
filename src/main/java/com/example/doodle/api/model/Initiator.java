package com.example.doodle.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.index.Indexed;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Initiator {
    public String name;
    @Indexed
    public String email;
    public Boolean notify;
    public String timeZone;
}