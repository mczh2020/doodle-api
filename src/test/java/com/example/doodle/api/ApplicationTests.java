package com.example.doodle.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest()
@ActiveProfiles("test")
class ApplicationTests {
    @Test
    void contextLoads() {
        //Expect: Spring context is loaded without any issues
    }
}
