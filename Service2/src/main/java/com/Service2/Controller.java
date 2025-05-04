package com.Service2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> healthCheck() {
        log.info("Hello called");
        return ResponseEntity.ok("Hello");
    }
}
