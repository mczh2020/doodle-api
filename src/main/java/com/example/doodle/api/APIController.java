package com.example.doodle.api;

import com.example.doodle.api.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1")
public class APIController {
    private final PollRepository repository;

    @Autowired
    public APIController(PollRepository repository) {
        this.repository = repository;
    }

    //1. List all polls created by a user
    @GetMapping("/polls")
    public ResponseEntity<List<Poll>> findPolls(@RequestParam(value = "email", required = false) String email,
                                                @RequestParam(value = "title", required = false) String title,
                                                @RequestParam(value = "created-after", required = false, defaultValue = "0") Long dateInEpoch) {
        if (isBlank(email) && isBlank(title) && dateInEpoch == 0) {
            return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(null);
        }
        if (!isBlank(email)) {
            return ResponseEntity.status(OK).body(repository.findByInitiatorEmail(email));
        }

        if (!isBlank(title)) {
            return ResponseEntity.status(OK).body(repository.findByTitle(title));
        }

        if (dateInEpoch != 0) {
            return ResponseEntity.status(OK).body(repository.findAllCreatedAfter(dateInEpoch));
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
    }


}
