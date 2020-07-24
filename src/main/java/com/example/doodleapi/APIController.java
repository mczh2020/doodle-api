package com.example.doodleapi;

import com.example.doodleapi.model.Poll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class APIController {
//1. List all polls created by a user
//2. Search polls by its title
//3. List all polls created after a certain date
    @GetMapping("/polls/email")
    public List<Poll> findPollsByUser(@RequestParam("query") String email){
        return Arrays.asList();
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
