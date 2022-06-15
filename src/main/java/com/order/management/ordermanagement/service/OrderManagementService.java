package com.order.management.ordermanagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.kafka.Producer;
import com.order.management.ordermanagement.mapper.OrderMapper;
import com.order.management.ordermanagement.model.Product;
import com.order.management.ordermanagement.repository.OrderDetails;
import com.order.management.ordermanagement.repository.OrderDetailsRepository;
import com.order.management.ordermanagement.utils.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * @author Exzion
 */

@Service
public class OrderManagementService {

    private static final Logger logger = LoggerFactory.getLogger(OrderManagementService.class);

    private final OrderDetailsRepository orderDetailRepository;
    private final OrderMapper orderMapper;
    private final ExecutorService executorService;
    private final Producer producer;

    @Value("${app.kafka.topic_name}")
    private String orderTopic;

    @Autowired
    public OrderManagementService(OrderDetailsRepository orderDetailRepository,
                                  OrderMapper orderMapper,
                                  @Qualifier("threadPoolTaskExecutor") ExecutorService executorService, Producer producer)
                                   {
        this.orderDetailRepository = orderDetailRepository;
        this.orderMapper = orderMapper;
        this.executorService = executorService;
        this.producer = producer;
 }


    public Optional<OrderDTO> submitOrder(OrderDTO orderDTO) {
        LocalDateTime rightNow = LocalDateTime.now();

        OrderDetails orderData = orderMapper.toEntity(orderDTO);
        orderData.setOrderId(UUID.randomUUID().toString());
       orderData.setCreatedTimestamp(rightNow);
       orderData.setUpdateTimestamp(rightNow);
       orderData.setOrderStatus(OrderStatus.PLACED.toString());
        List<Product> productList = orderDTO.getProductList();
        orderData.setTotalPrice(productList.stream()
                .mapToDouble(order -> order.getPrice()).sum());

        OrderDetails savedData = orderDetailRepository.save(orderData);
        CompletableFuture.runAsync(() -> sendKafkaMessage(orderData), executorService);
        return Optional.of(orderMapper.toDto(savedData));
    }

    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDto(orderDetailRepository.findAll());
    }

    public OrderDTO getOrderById(String orderId) {
        Optional<OrderDetails> byOrderId = orderDetailRepository.findByOrderId(orderId);
        return byOrderId.isPresent() ? orderMapper.toDto(byOrderId.get()) : new OrderDTO();
    }


    public void updateStatus(String orderDetails) {
        LocalDateTime rightNow = LocalDateTime.now();
        OrderDetails od;
        try {
            od = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(orderDetails, OrderDetails.class);
            Optional<OrderDetails> orderDetailsOptional = orderDetailRepository.findByOrderId(od.getOrderId());
            orderDetailsOptional.ifPresent(
                    order -> {
                        order.setOrderStatus(OrderStatus.PROCESSED.toString());
                        order.setUpdateTimestamp(rightNow);
                        orderDetailRepository.save(order);
                    });
        } catch (JsonProcessingException e) {
            logger.error("Unable to parse order details", e);
        }
    }


    public void sendKafkaMessage(OrderDetails orderData) {
        try {
            producer.postMessage(
                    orderTopic,
                    UUID.randomUUID().toString(),
            new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(orderData));
        } catch (JsonProcessingException e) {
            logger.error(
                    String.format("incorrect request body json exp order id :: {}", orderData.getOrderId(),
                            e.getMessage()));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("incorrect request body for order id :: {}", orderData.getOrderId()));
        }
    }




}
