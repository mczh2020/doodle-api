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
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
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
        // Given
        String email = "mh+sample@doodle.com";
        // When
        final ResponseEntity<List<Poll>> response = restCall("/polls/email?query=", email);
        // Then
        isTrue(response.getStatusCode() == HttpStatus.OK,
                format( "Return code must be 200 OK, when querying for user email:%s",email));
        isTrue(response.getBody().size() == 36);
    }

    @Test
    void findsPollsByTitle() {
        // Given
        String title = "Qui sont les superhéros Marvel les plus oufs?";
        // When
        final ResponseEntity<List<Poll>> response = restCall("/polls/title?query=", title);
        // Then
        isTrue(response.getStatusCode() == HttpStatus.OK,
                format( "Return code must be 200 OK, when querying for title:%s",title));
        isTrue(response.getBody().size() == 1);
    }

    @Test
    void findsPollsByTitlesWithNonASCIIChars() {
        // Given
        List<String> titles = Arrays.asList("ليتل جاي يتحول اثنين!",
                                            "小杰伊两岁！",
                                            "谁是最坏蛋奇迹超级英雄？");
        // When
        for (String title:titles) {
            ResponseEntity<List<Poll>> response = restCall("/polls/title?query=", title);
            isTrue(response.getStatusCode() == HttpStatus.OK,
                    "Return code must be 200 OK, when doing a get");
            isTrue(response.getBody().size() == 1,
                    format("For title:'%s', there should be exactly 1 match.",title));
            isTrue(response.getBody().get(0).title.contentEquals(title),
                    format("For title:'%s', what's returned from server should match exact.",title));
        }
    }

    @Test
    void findsPollsByCreationDate(){
        // Given
        // Found the epoch date to test with via "grep -oP 'initiated": \K([0-9]*)' polls.json | sort | tail -n2 | head -n1"
        String dateEpoch = "1485477127056";

        // When
        final ResponseEntity<List<Poll>> response = restCall("/polls/created-after?query=",dateEpoch);
        // Then
        isTrue(response.getStatusCode() == HttpStatus.OK,
                format( "Return code must be 200 OK, when querying for creation date:%s",dateEpoch));
        isTrue(response.getBody().size() == 1,
                format("For date:'%s', there should be exactly 1 match.",dateEpoch));
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