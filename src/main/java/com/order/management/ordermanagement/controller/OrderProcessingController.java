package com.order.management.ordermanagement.controller;

import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.service.OrderManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Exzion
 */

@RestController
@RequestMapping(value = "/orders")
public class OrderProcessingController {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingController.class);

    private OrderManagementService orderManagementService;

    @Autowired
    public OrderProcessingController(OrderManagementService orderManagementService) {
        this.orderManagementService = orderManagementService;
    }



    @RequestMapping(value = "/submit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> createApplication(
            @Validated @RequestBody OrderDTO orderDetails) {
        OrderDTO order = orderManagementService.submitOrder(orderDetails);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}
