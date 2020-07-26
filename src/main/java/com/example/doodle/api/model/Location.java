package com.example.doodle.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    @JsonProperty("name")
    public String name;
    @JsonProperty("address")
    public String address;
    @JsonProperty("countryCode")
    public String countryCode;
    @JsonProperty("locationId")
    public String locationId;
}