package com.order.management.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Exzion
 */

@Data
@AllArgsConstructor
public class ErrorDetails {

    private String errorCode;
    private String errorDesc;


}
