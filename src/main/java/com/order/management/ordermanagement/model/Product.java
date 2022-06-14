package com.order.management.ordermanagement.model;


import lombok.*;

/**
 * @author Exzion
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Getter
    @Setter
    private String productId;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private String productType;
}
