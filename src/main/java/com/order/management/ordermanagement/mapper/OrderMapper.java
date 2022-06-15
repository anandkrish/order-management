package com.order.management.ordermanagement.mapper;

import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.repository.OrderDetails;
import com.order.management.ordermanagement.utils.EntityMapper;
import org.mapstruct.*;

/**
 * @author Exzion
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, OrderDetails> {

}
