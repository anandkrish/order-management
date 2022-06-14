package com.order.management.ordermanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Exzion
 */

@RestController
public class TestController {


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ResponseEntity<String> testEndPoint() {
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
