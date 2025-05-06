package com.Service1;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.Service1.service.S1Service;
import com.Service1.vo.RequestConcat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @Mock
    private S1Service s1Service;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testHealthCheck() {
        ResponseEntity<String> response = controller.healthCheck();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Up", response.getBody());
    }

    @Test
    void testGetConcat_success() throws Exception {
        RequestConcat requestConcat = RequestConcat.builder().Name("John").Surname("Doe").build();
        when(s1Service.getFinalResponse(requestConcat)).thenReturn("Hello John Doe");

        ResponseEntity<String> response = controller.GetConcat(requestConcat);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello John Doe", response.getBody());
        verify(s1Service, times(1)).getFinalResponse(requestConcat);
    }

    @Test
    void testGetConcat_exceptionHandling() throws Exception {
        RequestConcat requestConcat = RequestConcat.builder().Name("John").Surname("Doe").build();
        when(s1Service.getFinalResponse(requestConcat)).thenThrow(new Exception("Service Error"));

        ResponseEntity<String> response = controller.GetConcat(requestConcat);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occurred in processing request", response.getBody());
        verify(s1Service, times(1)).getFinalResponse(requestConcat);
    }
}