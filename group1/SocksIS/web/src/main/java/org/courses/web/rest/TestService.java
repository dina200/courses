package org.courses.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dbtest")
public class TestService {

    @GetMapping(value = "/{connectionstring}")
    public ResponseEntity greeting(@PathVariable("connectionstring") String connectionString) {
        HttpStatus status = null == connectionString ?
                HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity(status);
    }
}
