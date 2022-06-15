package com.order.management.ordermanagement.response;

import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.model.OrderError;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Exzion
 */
@Data
public class OrderResponse {
    private OrderDTO orderDetails;

    private OrderError orderError;

    public OrderResponse(OrderDTO orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderResponse(OrderError orderError) {
        this.orderError = orderError;
    }

    public OrderResponse(OrderDTO orderDetails, OrderError orderError) {
        this.orderDetails = orderDetails;
        this.orderError = orderError;
    }
}
