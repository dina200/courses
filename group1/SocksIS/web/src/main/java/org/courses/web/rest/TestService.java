package org.courses.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dbtest")
public class TestService {

    @GetMapping(value = "/greeting")
    public ResponseEntity greeting(@RequestParam(value = "connectionstring") String connectionString) {
        HttpStatus status = null == connectionString ?
                HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity(status);
    }
}
