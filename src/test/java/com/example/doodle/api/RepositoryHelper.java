package com.example.doodle.api;

import com.example.doodle.api.model.Poll;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@ActiveProfiles("test")
public class RepositoryHelper {
    @Autowired
    private PollRepository pollRepository;

    @PostConstruct
    public void parseAndImportTestData() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/polls.json")) {
            final List<Poll> polls = mapper.readValue(inputStream, new TypeReference<>() {
            });
            for (Poll poll : polls) {
                pollRepository.save(poll);
            }
        }
    }
}
