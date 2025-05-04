package com.Service1.service;

import com.Service1.Adapter;
import com.Service1.vo.RequestConcat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class S1Service {

    @Autowired
    private Adapter adapter;

    public String getFinalResponse(RequestConcat requestConcat) throws Exception {

        try {
            log.info("Getting final response for request {}", requestConcat);
            String resp1 = adapter.callHelloApi();
            String resp2 = adapter.callConcatApi(requestConcat);

            return resp1 + " " + resp2;
        } catch (Exception e) {
            log.error("Error occured in geting final response for request {}", requestConcat, e);
            throw e;
        }
    }
}
