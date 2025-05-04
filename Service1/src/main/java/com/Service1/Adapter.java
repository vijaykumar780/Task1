package com.Service1;

import com.Service1.vo.RequestConcat;
import com.Service3.vo.RequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class Adapter {
    RestTemplate restTemplate = new RestTemplate();

    @Value("${s3.concat.url}")
    private String s3ConcatUrl;

    @Value("${s2.hello.url}")
    private String s2HelloUrl;

    public String callConcatApi(RequestConcat requestVo) throws Exception {

        try {
            log.info("Calling concat api with requst: {}", requestVo);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RequestConcat> requestEntity = new HttpEntity<>(requestVo, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(s3ConcatUrl, requestEntity, String.class);
            return response.toString();
        } catch (Exception e) {
            log.error("Error occured in calling concat api, request {}", requestVo, e);
            throw e;
        }
    }

    public String callHelloApi() throws Exception {
        try {
            log.info("Calling concat api");
            ResponseEntity<String> response = restTemplate.getForEntity(s2HelloUrl, String.class);
            return response.toString();
        } catch (Exception e) {
            log.error("Error occured in calling concat api", e);
            throw e;
        }
    }

}
