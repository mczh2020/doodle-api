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

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

import static java.net.URLEncoder.encode;
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
    void findsPollsByUser(){
        // When
        final ResponseEntity<List<Poll>> response = restCall("/polls/email?query=", "mh+sample@doodle.com");
        // Expect
        isTrue(response.getStatusCode() == HttpStatus.OK, "Return code must be 200 OK, when doing a get");
        isTrue(response.getBody().size() == 36);
    }

    @Test
    void findsPollsByTitle() {
        // When
        final ResponseEntity<List<Poll>> response = restCall("/polls/title?query=", "Qui sont les superhéros Marvel les plus oufs?");
        // Expect
        isTrue(response.getStatusCode() == HttpStatus.OK, "Return code must be 200 OK, when doing a get");
        isTrue(response.getBody().size() == 1);
    }

    @Test
    void findsPollsByCreationDate(){
        // When
        final ResponseEntity<List<Poll>> response = restCall("/polls/created-after?query=","1485477127056");
        // Expect
        isTrue(response.getStatusCode() == HttpStatus.OK, "Return code must be 200 OK, when doing a get");
        isTrue(response.getBody().size() == 1);

    }

    private ResponseEntity<List<Poll>> restCall(String path, String query)  {
        try {
            return restTemplate.exchange(
                    createURIWithPort(path + encode(query, "UTF-8")),
                    GET, null, new ParameterizedTypeReference<List<Poll>>() {
                    });
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }
    private URI createURIWithPort(String path) {
        return URI.create("http://localhost:" + port + path);
    }
}
