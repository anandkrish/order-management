package com.order.management.ordermanagement.kafka;

import com.order.management.ordermanagement.service.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Exzion
 */

@Service
public class Consumer {

    private final OrderManagementService orderManagementService;

    @Autowired
    public Consumer(OrderManagementService orderManagementService) {
        this.orderManagementService = orderManagementService;
    }

    @KafkaListener(topics = "${app.kafka.topic_name}", groupId = "order_consumer")
    public void listenToOrderDetails(String orderDetails) {
        orderManagementService.updateStatus(orderDetails);
    }
}
