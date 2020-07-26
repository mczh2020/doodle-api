package com.example.doodle.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    public String name;
    public String address;
    public String countryCode;
    public String locationId;
}