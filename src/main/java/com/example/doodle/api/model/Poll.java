package com.example.doodle.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Poll {
    @Id
    public String id;
    public String adminKey;
    public Long latestChange;
    @Indexed
    public Long initiated;
    public Long participantsCount;
    public Long inviteesCount;
    public String type;
    public Boolean hidden;
    public String preferencesType;
    public String state;
    public String locale;
    @Indexed
    public String title;
    public String description;
    public Initiator initiator;
    public List<Option> options = new ArrayList<Option>();
    public String optionsHash;
    public List<Participant> participants = new ArrayList<Participant>();
    public List<Object> invitees = new ArrayList<Object>();
    public String device;
    public String levels;
    public Location location;
    public Boolean multiDay;
    public Long columnConstraint;
    public Boolean dateText;
    public Boolean timeZone;
    public Long rowConstraint;
}

