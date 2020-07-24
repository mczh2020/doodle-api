package com.example.doodle.api;

import com.example.doodle.api.model.Initiator;
import com.example.doodle.api.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class APIController {
    private final PollRepository repository;

    @Autowired
    public APIController(PollRepository repository) {
        this.repository = repository;
    }

    //1. List all polls created by a user
    //2. Search polls by its title
    //3. List all polls created after a certain date
    @GetMapping("/polls/email")
    public List<Poll> findPollsByUser(@RequestParam("query") String email){
        Poll examplePoll = new Poll();
        examplePoll.initiator = new Initiator();
        examplePoll.initiator.email = email;
        return repository.findAll(Example.of(examplePoll));
    }

    @GetMapping("/polls/title")
    public List<Poll> findPollsByTitle(@RequestParam("query") String title){
        return Arrays.asList();
    }

    @GetMapping("/polls/created-after")
    public List<Poll> findPollsByCreationDate(@RequestParam("query") String date){
        return Arrays.asList();
    }
}
