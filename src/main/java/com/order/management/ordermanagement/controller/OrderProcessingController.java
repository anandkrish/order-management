package com.order.management.ordermanagement.controller;

import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.model.ErrorDetails;
import com.order.management.ordermanagement.model.OrderError;
import com.order.management.ordermanagement.response.OrderResponse;
import com.order.management.ordermanagement.service.OrderManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

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



    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createApplication(
            @Validated @RequestBody OrderDTO orderDetails) {
        Optional<OrderDTO> order = orderManagementService.submitOrder(orderDetails);
        if(order.isPresent()) {
            return new ResponseEntity<>(new OrderResponse(List.of(order.get())), HttpStatus.CREATED);

        } else {
            List<ErrorDetails> errorDetails = List.of(new ErrorDetails("101","Order submission failed"));
            OrderError orderError = new OrderError(-1, "", errorDetails);
            return new ResponseEntity<>
                    (new OrderResponse(orderError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> getAllOrders() {
        List<OrderDTO> orderDetailsList = orderManagementService.getAllOrders();
        return new ResponseEntity<>(new OrderResponse(orderDetailsList), HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable(value = "orderId") @NotBlank final String orderId) {
        OrderDTO orderById = orderManagementService.getOrderById(orderId);
        return new ResponseEntity<>(new OrderResponse(List.of(orderById)), HttpStatus.OK);
    }

}
