package com.example.doodle.api;

import com.example.doodle.api.model.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PollRepository extends MongoRepository<Poll, String> {
    @Query(value = "{ 'initiator.email' : ?0 }")
    List<Poll> findByInitiatorEmail(String email);

    @Query(value = "{'title' : ?0}")
    List<Poll> findByTitle(String title);

    @Query(value = "{'initiated' > ?0}")
    List<Poll> findAllCreatedAfter(Long dateInEpoch);

}
