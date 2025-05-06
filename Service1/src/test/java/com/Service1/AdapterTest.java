package com.Service1;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.Service1.vo.RequestConcat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class AdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private Adapter adapter;

    @BeforeEach
    void setUp() {
        // Set mock URLs (Can use ReflectionTestUtils if needed)
        adapter.s3ConcatUrl = "http://127.0.0.1/concat";
        adapter.s2HelloUrl = "http://127.0.0.1/hello";
    }

    @Test
    void testCallConcatApi_success() throws Exception {
        RequestConcat requestConcat = RequestConcat.builder().Name("John").Surname("Doe").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestConcat> requestEntity = new HttpEntity<>(requestConcat, headers);

        ResponseEntity<String> mockResponse = ResponseEntity.ok("John Doe");
        when(restTemplate.postForEntity(adapter.s3ConcatUrl, requestEntity, String.class)).thenReturn(mockResponse);

        String response = adapter.callConcatApi(requestConcat);

        assertEquals("John Doe", response);
        verify(restTemplate, times(1)).postForEntity(adapter.s3ConcatUrl, requestEntity, String.class);
    }

    @Test
    void testCallConcatApi_exceptionHandling() {
        RequestConcat requestConcat = RequestConcat.builder().Name("John").Surname("Doe").build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestConcat> requestEntity = new HttpEntity<>(requestConcat, headers);

        when(restTemplate.postForEntity(adapter.s3ConcatUrl, requestEntity, String.class))
                .thenThrow(new RuntimeException("Concat API Failure"));

        Exception exception = assertThrows(Exception.class, () -> adapter.callConcatApi(requestConcat));

        assertEquals("Concat API Failure", exception.getMessage());
        verify(restTemplate, times(1)).postForEntity(adapter.s3ConcatUrl, requestEntity, String.class);
    }

    @Test
    void testCallHelloApi_success() throws Exception {
        ResponseEntity<String> mockResponse = ResponseEntity.ok("Hello");
        when(restTemplate.getForEntity(adapter.s2HelloUrl, String.class)).thenReturn(mockResponse);

        String response = adapter.callHelloApi();

        assertEquals("Hello", response);
        verify(restTemplate, times(1)).getForEntity(adapter.s2HelloUrl, String.class);
    }

    @Test
    void testCallHelloApi_exceptionHandling() {
        when(restTemplate.getForEntity(adapter.s2HelloUrl, String.class))
                .thenThrow(new RuntimeException("Hello API Failure"));

        Exception exception = assertThrows(Exception.class, () -> adapter.callHelloApi());

        assertEquals("Hello API Failure", exception.getMessage());
        verify(restTemplate, times(1)).getForEntity(adapter.s2HelloUrl, String.class);
    }
}