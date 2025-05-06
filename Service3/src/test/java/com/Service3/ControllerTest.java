package com.Service3;
import static org.junit.jupiter.api.Assertions.*;

import com.Service3.vo.RequestVo;
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
    void testConcat_success() {
        RequestVo requestVo = RequestVo.builder().Name("John").Surname("Doe").build();

        ResponseEntity<String> response = controller.concat(requestVo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody());
    }
}