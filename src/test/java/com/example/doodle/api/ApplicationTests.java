package com.example.doodle.api;

import com.example.doodle.api.model.Poll;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApplicationTests {
    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @Autowired
    private PollRepository pollRepository;

    @BeforeAll
    void prepareTestData() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        try ( InputStream inputStream =  Thread.currentThread().getContextClassLoader().getResourceAsStream("data/polls.json")){
            final List<Poll> polls = mapper.readValue(inputStream,new TypeReference<List<Poll>>() {});
            for (Poll poll:polls) {
                pollRepository.save(poll);
            }
        }
    }

    @Test
    void contextLoads() {
    }

    @Test
    void findsPollsByUser() {
        // When
        final ResponseEntity<List<Poll>> response = restTemplate.exchange(
				createURLWithPort("/polls/email?query=test@test.com"),
				GET, null, new ParameterizedTypeReference<List<Poll>>() {
				});
        // Expect
        isTrue(response.getStatusCode() == HttpStatus.OK, "Return code must be 200 OK, when doing a get");
    }

    @Test
    void findsPollsByTitle() {
        // When
        final ResponseEntity<List<Poll>> response = restTemplate.exchange(
                createURLWithPort("/polls/title?query=title"),
                GET, null, new ParameterizedTypeReference<List<Poll>>() {
                });
        // Expect
        isTrue(response.getStatusCode() == HttpStatus.OK, "Return code must be 200 OK, when doing a get");
    }


    @Test
    void findsPollsByCreationDate() {
        // When
        final ResponseEntity<List<Poll>> response = restTemplate.exchange(
                createURLWithPort("/polls/created-after?query=01.07.2020"),
                GET, null, new ParameterizedTypeReference<List<Poll>>() {
                });
        // Expect
        isTrue(response.getStatusCode() == HttpStatus.OK, "Return code must be 200 OK, when doing a get");
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }



}
