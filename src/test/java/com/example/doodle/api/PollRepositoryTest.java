package com.example.doodle.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.util.Assert.isTrue;

@SpringBootTest
public class PollRepositoryTest {
    @Autowired
    private PollRepository pollRepository;

    @Test
    public void queryByEmail() {
        isTrue(pollRepository.findByInitiatorEmail("mh+sample@doodle.com").size() == 36,
                "All instances of mh+sample@doodle.com should be found.");
    }

    @Test
    public void queryByTitle() {
        isTrue(pollRepository.findByTitle("Qui sont les superhéros Marvel les plus oufs?").size() == 1,
                "There should only be one poll with requested title");
    }

    @Test
    public void queryByCreationDate() {
        // Find the epoch date to test with via "grep -oP 'initiated": \K([0-9]*)' polls.json | sort | tail -n2 | head -n1"
        isTrue(pollRepository.findAllCreatedAfter(1485477127056L).size() == 1,
                "There should only be one poll that is later than the requested date");
    }

}
