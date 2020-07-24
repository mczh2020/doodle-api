package com.example.doodleapi.model;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    public String id;
    public String adminKey;
    public Long latestChange;
    public Long initiated;
    public Long participantsCount;
    public Long inviteesCount;
    public String type;
    public Boolean hidden;
    public String preferencesType;
    public String state;
    public String locale;
    public String title;
    public Initiator initiator;
    public List<Option> options = new ArrayList<Option>();
    public String optionsHash;
    public List<Participant> participants = new ArrayList<Participant>();
    public List<Object> invitees = new ArrayList<Object>();
    public String device;
    public String levels;
}

