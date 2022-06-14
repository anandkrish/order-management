package com.order.management.ordermanagement.mapper;

import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.repository.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-15T01:40:28+0530",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDetails toEntity(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrderDetails orderDetails = new OrderDetails();

        return orderDetails;
    }

    @Override
    public OrderDTO toDto(OrderDetails entity) {
        if ( entity == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        return orderDTO;
    }

    @Override
    public List<OrderDetails> toEntity(List<OrderDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<OrderDetails> list = new ArrayList<OrderDetails>( dtoList.size() );
        for ( OrderDTO orderDTO : dtoList ) {
            list.add( toEntity( orderDTO ) );
        }

        return list;
    }

    @Override
    public List<OrderDTO> toDto(List<OrderDetails> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( entityList.size() );
        for ( OrderDetails orderDetails : entityList ) {
            list.add( toDto( orderDetails ) );
        }

        return list;
    }
}
