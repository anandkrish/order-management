package com.order.management.ordermanagement.service;

import com.order.management.ordermanagement.repository.user.UserLogin;
import com.order.management.ordermanagement.repository.user.UserLoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Exzion
 */

@Service
public class UserAuthService {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthService.class);

    private final UserLoginRepository userLoginRepository;


    public UserAuthService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }


    public String createUserToken(String userId) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(userId);
        userLogin.setAuthToken(UUID.randomUUID().toString());
        UserLogin save = userLoginRepository.save(userLogin);
        return save.getAuthToken();
    }

    public Optional<UserLogin> getUserById(String userId) {
       return userLoginRepository.findByUserId(userId);
    }
}
