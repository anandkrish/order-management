package com.order.management.ordermanagement.controller;

import com.order.management.ordermanagement.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Exzion
 */


@RestController
public class LoginController {

    @Value("${app.auth.header}")
    private String authHeader;

    @Value("${app.auth.token}")
    private String authToken;


    private UserAuthService userAuthService;

    @Autowired
    public LoginController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes =
            MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody String userId) {
        String userToken = userAuthService.createUserToken(userId);
        return new ResponseEntity<>(userToken, HttpStatus.OK);
    }
}
