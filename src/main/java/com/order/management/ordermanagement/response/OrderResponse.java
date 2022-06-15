package com.order.management.ordermanagement.response;

import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.model.OrderError;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Exzion
 */
@Data
public class OrderResponse {
    private List<OrderDTO> orderDetails;

    private OrderError orderError;

    public List<OrderDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderError getOrderError() {
        return orderError;
    }

    public void setOrderError(OrderError orderError) {
        this.orderError = orderError;
    }

    public OrderResponse(OrderError orderError) {
        this.orderError = orderError;
    }

    public OrderResponse(List<OrderDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
