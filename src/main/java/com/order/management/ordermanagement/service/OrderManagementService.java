package com.order.management.ordermanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.mapper.OrderMapper;
import com.order.management.ordermanagement.model.Product;
import com.order.management.ordermanagement.repository.OrderDetails;
import com.order.management.ordermanagement.repository.OrderDetailsRepository;
import com.order.management.ordermanagement.utils.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Exzion
 */

@Service
public class OrderManagementService {

    private static final Logger logger = LoggerFactory.getLogger(OrderManagementService.class);

    private final OrderDetailsRepository orderDetailRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderManagementService(OrderDetailsRepository orderDetailRepository, OrderMapper orderMapper)
                                   {
        this.orderDetailRepository = orderDetailRepository;
        this.orderMapper = orderMapper;
    }


    public OrderDTO submitOrder(OrderDTO orderDTO) {
        LocalDateTime rightNow = LocalDateTime.now();
        OrderDetails orderData = new OrderDetails();
        orderData.setOrderId(UUID.randomUUID().toString());
        orderData.setCustomerName(orderDTO.getCustomerName());
        orderData.setShipmentAddress(orderDTO.getShipmentAddress());
        orderData.setPhoneNumber(orderDTO.getPhoneNumber());
        orderData.setOrderStatus(OrderStatus.PLACED.toString());
        orderData.setProductList(orderDTO.getProductList());
        orderData.setCreatedTimestamp(rightNow);
        orderData.setUpdateTimestamp(rightNow);
        List<Product> productList = orderDTO.getProductList();

        orderData.setTotalPrice(productList.stream()
                .mapToDouble(order -> order.getPrice()).sum());
        OrderDetails savedData = orderDetailRepository.save(orderData);
        // TODO:: replace with mapstruct

        return orderDTO;
    }
}
