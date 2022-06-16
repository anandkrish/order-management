package com.order.management.ordermanagement;

import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.kafka.Consumer;
import com.order.management.ordermanagement.kafka.Producer;
import com.order.management.ordermanagement.model.Product;
import com.order.management.ordermanagement.repository.order.OrderDetailsRepository;
import com.order.management.ordermanagement.service.OrderManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.timeout;

import java.util.List;
import java.util.UUID;


@SpringBootTest
public class OrderManagementServiceTest {

    @MockBean
    Producer mockProducer;
    @Mock
    private OrderDetailsRepository orderDetailsRepository;

    @MockBean
    Consumer consumer;


    @Autowired
    OrderManagementService orderManagementService;

    @Value("${app.kafka.topic_name}")
    private String topic;


    @BeforeEach
    public void setup() {
        Mockito.doNothing()
                .when(mockProducer)
                .postMessage(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @DisplayName("Submit Order and Get Order details test")
    @Test
    void testSubmitOrder() {

        String id = UUID.randomUUID().toString();
        OrderDTO mockOrder = Mockito.mock(OrderDTO.class);
        Product mockProduct = Mockito.mock(Product.class);
        mockOrder.setOrderId(id);
        mockOrder.setOrderStatus("PLACED");
        mockOrder.setCustomerId("exzion");
        mockOrder.setCustomerName("Anand");
        mockOrder.setPhoneNumber("9952563200");
        mockProduct.setPrice(100.00);
        mockProduct.setProductId("A123");
        mockProduct.setProductName("Dress21");
        mockProduct.setProductType("Fashion");
        mockOrder.setProductList(List.of(mockProduct));

        OrderDTO orderDTO = orderManagementService.submitOrder(mockOrder).get();
        Assertions.assertNotNull(orderDTO.getOrderId());
    }

    @DisplayName("Test Kafka push")
    @Test
    void testKafka() {

        String id = UUID.randomUUID().toString();
        OrderDTO mockOrder = Mockito.mock(OrderDTO.class);
        Product mockProduct = Mockito.mock(Product.class);
        mockOrder.setOrderId(id);
        mockOrder.setOrderStatus("PLACED");
        mockOrder.setCustomerId("exzion");
        mockOrder.setCustomerName("Anand");
        mockOrder.setPhoneNumber("9952563200");
        mockProduct.setPrice(100.00);
        mockProduct.setProductId("A123");
        mockProduct.setProductName("Dress21");
        mockProduct.setProductType("Fashion");
        mockOrder.setProductList(List.of(mockProduct));

        orderManagementService.submitOrder(mockOrder).get();
        Mockito.verify(mockProducer, timeout(2000))
                .postMessage(
                        Mockito.anyString(), Mockito.anyString(), Mockito.contains("\"orderStatus\":\"PLACED\""));
    }
}