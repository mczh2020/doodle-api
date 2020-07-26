package com.example.doodle.api;

import com.example.doodle.api.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class APIController {
    private final PollRepository repository;

    @Autowired
    public APIController(PollRepository repository) {
        this.repository = repository;
    }

    //1. List all polls created by a user
    @GetMapping("/polls/email")
    public List<Poll> findPollsByUser(@RequestParam("query") String email){
        return repository.findByInitiatorEmail(email);
    }

    //2. Search polls by its title
    @GetMapping("/polls/title")
    public List<Poll> findPollsByTitle(@RequestParam("query") String title){
        return repository.findByTitle(title);
    }

    //3. List all polls created after a certain date
    @GetMapping("/polls/created-after")
    public List<Poll> findPollsByCreationDate(@RequestParam("query") Long dateInEpoch) {
        return repository.findAllCreatedAfter(dateInEpoch);
    }
}
