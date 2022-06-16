package com.order.management.ordermanagement.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Exzion
 */
public interface UserLoginRepository extends MongoRepository<UserLogin,String> {


    Optional<UserLogin> findByUserId(String userId);

}
