package com.order.management.ordermanagement.repository;

import com.order.management.ordermanagement.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Exzion
 */

@Setter
@Getter
@Document(collection = "orderDetails")
public class OrderDetails implements Serializable {

    @Id
    @Indexed
    private String orderId;

    @Field("expectedDeliveryDate")
    private String expectedDeliveryDate;

    @Field("actualDeliveryDate")
    private String actualDeliveryDate;

    @Field("shipmentAddress")
    private String shipmentAddress;

    @Field("customerName")
    private String customerName;

    @Field("customerId")
    private String customerId;

    @Field("phoneNumber")
    private String phoneNumber;

    @Field("totalPrice")
    private Double totalPrice; // store in lowest denomination

    @Field("orderStatus")
    private  String orderStatus;

    @Field("productList")
    private List<Product> productList;

    @Field("createdTimestamp")
    private LocalDateTime createdTimestamp;

    @Field("updateTimestamp")
    private LocalDateTime updateTimestamp;

}
