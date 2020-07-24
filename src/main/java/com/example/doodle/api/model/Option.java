package com.example.doodle.api.model;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Option {
    @JsonProperty("allday")
    public Boolean allday;
    @JsonProperty("available")
    public Boolean available;
    @JsonProperty("startDateTime")
    @JsonAlias({"startDate", "start","dateTime"})
    public Long startDateTime;
    @JsonProperty("endDateTime")
    @JsonAlias({"endDate", "end"})
    public Long endDateTime;
    @JsonProperty("text")
    public String text;
    @JsonProperty("date")
    public Long date;
}
