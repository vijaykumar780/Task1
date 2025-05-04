package com.Service3;

import com.Service3.vo.RequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    @RequestMapping(value = "/concat", method = RequestMethod.POST)
    public ResponseEntity<String> concat(@RequestBody RequestVo requestVo) {
        log.info("Request rec. with Json {}", requestVo);
        return ResponseEntity.ok(requestVo.getName() + " " + requestVo.getSurname());
    }
}
