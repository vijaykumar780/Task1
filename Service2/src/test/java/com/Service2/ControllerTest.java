package com.Service2;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testHelloEndpoint() {
        ResponseEntity<String> response = controller.healthCheck();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello", response.getBody());
    }
}