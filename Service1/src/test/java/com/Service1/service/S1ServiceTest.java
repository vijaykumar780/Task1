package com.Service1.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.Service1.Adapter;
import com.Service1.vo.RequestConcat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class S1ServiceTest {

    @Mock
    private Adapter adapter;

    @InjectMocks
    private S1Service s1Service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetFinalResponse_success() throws Exception {
        RequestConcat requestConcat = RequestConcat.builder().Name("John").Surname("Doe").build();
        when(adapter.callHelloApi()).thenReturn("Hello");
        when(adapter.callConcatApi(requestConcat)).thenReturn("John Doe");

        String result = s1Service.getFinalResponse(requestConcat);

        assertEquals("Hello John Doe", result);
        verify(adapter, times(1)).callHelloApi();
        verify(adapter, times(1)).callConcatApi(any());
    }

    @Test
    void testGetFinalResponse_exception() throws Exception {
        RequestConcat requestConcat = RequestConcat.builder().Name("John").Surname("Doe").build();
        when(adapter.callHelloApi()).thenThrow(new RuntimeException("API failure"));

        Exception exception = assertThrows(Exception.class, () ->
                s1Service.getFinalResponse(requestConcat));

        assertEquals("API failure", exception.getMessage());
        verify(adapter, times(1)).callHelloApi();
        verify(adapter, never()).callConcatApi(requestConcat);
    }
}