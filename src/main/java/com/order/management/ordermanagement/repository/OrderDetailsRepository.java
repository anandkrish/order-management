package com.order.management.ordermanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Exzion
 */
@Repository
public interface OrderDetailsRepository extends MongoRepository<OrderDetails,String> {

}
