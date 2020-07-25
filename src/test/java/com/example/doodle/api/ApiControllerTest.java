package com.example.doodle.api;

import com.example.doodle.api.model.Poll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private int port;
    @Autowired
    private RepositoryHelper repositoryHelper;

    @Test
    void findsPollsByUser() {
        // When
        final ResponseEntity<List<Poll>> response = restTemplate.exchange(
                createURLWithPort("/polls/email?query=" + "mh+sample@doodle.com"),
                GET, null, new ParameterizedTypeReference<List<Poll>>() {
                });
        // Expect
        isTrue(response.getStatusCode() == HttpStatus.OK, "Return code must be 200 OK, when doing a get");
        isTrue(response.getBody().size() == 36);
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
                createURLWithPort("/polls/created-after?query=1485477127056"),
                GET, null, new ParameterizedTypeReference<List<Poll>>() {
                });
        // Expect
        isTrue(response.getStatusCode() == HttpStatus.OK, "Return code must be 200 OK, when doing a get");
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
