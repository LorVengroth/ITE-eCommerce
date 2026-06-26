package ite_3rd_ecommerce.co.stad.project.feature.order.service;

import ite_3rd_ecommerce.co.stad.project.feature.order.dto.CreateOrderRequest;
import ite_3rd_ecommerce.co.stad.project.feature.order.dto.OrderResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {

    OrderResponse createNew(CreateOrderRequest createOrderRequest);
    Page<OrderResponse> findAll(int pageNumber , int pageSize);
    OrderResponse findById(UUID id);
    OrderResponse softDeleteById(UUID id);
    void deleteById(UUID id);
    OrderResponse setPaymentById(UUID id);


}
