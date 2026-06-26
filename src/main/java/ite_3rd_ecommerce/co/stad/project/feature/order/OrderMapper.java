package ite_3rd_ecommerce.co.stad.project.feature.order;


import ite_3rd_ecommerce.co.stad.project.feature.order.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    OrderResponse mapOrderToOrderResponse(Order order);

}
