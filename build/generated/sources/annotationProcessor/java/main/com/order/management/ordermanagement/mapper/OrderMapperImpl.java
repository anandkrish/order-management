package com.order.management.ordermanagement.mapper;

import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.model.Product;
import com.order.management.ordermanagement.repository.OrderDetails;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-15T23:54:27+0530",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    private final DatatypeFactory datatypeFactory;

    public OrderMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public OrderDetails toEntity(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setOrderId( dto.getOrderId() );
        orderDetails.setExpectedDeliveryDate( dto.getExpectedDeliveryDate() );
        orderDetails.setActualDeliveryDate( dto.getActualDeliveryDate() );
        orderDetails.setShipmentAddress( dto.getShipmentAddress() );
        orderDetails.setCustomerName( dto.getCustomerName() );
        orderDetails.setCustomerId( dto.getCustomerId() );
        orderDetails.setPhoneNumber( dto.getPhoneNumber() );
        orderDetails.setTotalPrice( dto.getTotalPrice() );
        orderDetails.setOrderStatus( dto.getOrderStatus() );
        List<Product> list = dto.getProductList();
        if ( list != null ) {
            orderDetails.setProductList( new ArrayList<Product>( list ) );
        }
        orderDetails.setCreatedTimestamp( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( dto.getCreatedTimestamp() ) ) );
        orderDetails.setUpdateTimestamp( dto.getUpdateTimestamp() );

        return orderDetails;
    }

    @Override
    public OrderDTO toDto(OrderDetails entity) {
        if ( entity == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId( entity.getOrderId() );
        orderDTO.setExpectedDeliveryDate( entity.getExpectedDeliveryDate() );
        orderDTO.setActualDeliveryDate( entity.getActualDeliveryDate() );
        orderDTO.setShipmentAddress( entity.getShipmentAddress() );
        orderDTO.setCustomerName( entity.getCustomerName() );
        orderDTO.setCustomerId( entity.getCustomerId() );
        orderDTO.setPhoneNumber( entity.getPhoneNumber() );
        orderDTO.setTotalPrice( entity.getTotalPrice() );
        orderDTO.setOrderStatus( entity.getOrderStatus() );
        List<Product> list = entity.getProductList();
        if ( list != null ) {
            orderDTO.setProductList( new ArrayList<Product>( list ) );
        }
        orderDTO.setCreatedTimestamp( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( entity.getCreatedTimestamp() ) ) );
        orderDTO.setUpdateTimestamp( entity.getUpdateTimestamp() );

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

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate ) {
        if ( localDate == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendarDate(
            localDate.getYear(),
            localDate.getMonthValue(),
            localDate.getDayOfMonth(),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }

    private static LocalDateTime xmlGregorianCalendarToLocalDateTime( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        if ( xcal.getYear() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMonth() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getDay() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getHour() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMinute() != DatatypeConstants.FIELD_UNDEFINED
        ) {
            if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED
                && xcal.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond(),
                    Duration.ofMillis( xcal.getMillisecond() ).getNano()
                );
            }
            else if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond()
                );
            }
            else {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute()
                );
            }
        }
        return null;
    }
}
