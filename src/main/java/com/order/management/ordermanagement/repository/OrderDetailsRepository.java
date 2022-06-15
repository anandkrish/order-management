package com.order.management.ordermanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Exzion
 */
@Repository
public interface OrderDetailsRepository extends MongoRepository<OrderDetails,String> {

    Optional<OrderDetails> findByOrderId(String orderId);
}
