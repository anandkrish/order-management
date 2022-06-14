package com.order.management.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Exzion
 */

@Data
@AllArgsConstructor
public class Error {

    private int status;

    private  String message;

    private List<ErrorDetails> errorDetails;
}
