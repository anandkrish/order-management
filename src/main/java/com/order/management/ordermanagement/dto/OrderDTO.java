package com.order.management.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.order.management.ordermanagement.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Exzion
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {

    private String orderId;

    private String expectedDeliveryDate;

    private String actualDeliveryDate;

    private String shipmentAddress;

    private String customerName;

    private String customerId;

    private String phoneNumber;

    private Double totalPrice; // store in lowest denomination

    private  String orderStatus; //TODO: change this to enum

    private List<Product> productList;

    private LocalDate createdTimestamp;


}
