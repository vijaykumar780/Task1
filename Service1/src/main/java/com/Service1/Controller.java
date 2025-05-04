package com.Service1;

import com.Service1.service.S1Service;
import com.Service1.vo.RequestConcat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private S1Service s1Service;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResponseEntity<String> healthCheck() {
        log.info("Service is up");
        return ResponseEntity.ok("Up");
    }

    @RequestMapping(value = "/GetConcat", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> GetConcat(@RequestBody RequestConcat requestConcat) {
        // request vo has name and surname in it
        log.info("Request received {}", requestConcat);
        try {
            return ResponseEntity.ok(s1Service.getFinalResponse(requestConcat));
        } catch (Exception e) {
            log.error("Error processing request {}", requestConcat);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred in processing request");
        }

    }
}
