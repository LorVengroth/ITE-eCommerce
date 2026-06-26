package ite_3rd_ecommerce.co.stad.project.feature.order.service;


import ite_3rd_ecommerce.co.stad.project.feature.file.FileUpload;
import ite_3rd_ecommerce.co.stad.project.feature.order.Order;
import ite_3rd_ecommerce.co.stad.project.feature.order.OrderLine;
import ite_3rd_ecommerce.co.stad.project.feature.order.OrderMapper;
import ite_3rd_ecommerce.co.stad.project.feature.order.OrderRepository;
import ite_3rd_ecommerce.co.stad.project.feature.order.dto.CreateOrderRequest;
import ite_3rd_ecommerce.co.stad.project.feature.order.dto.OrderResponse;
import ite_3rd_ecommerce.co.stad.project.feature.product.Product;
import ite_3rd_ecommerce.co.stad.project.feature.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository ;
    private final ProductRepository productRepository ;
    private final OrderMapper orderMapper ;

    @Override
    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {


        final  Order order = new Order();
        order.setAddress(createOrderRequest.address());
        order.setCustomerId("Vengroth");
        order.setDiscount(createOrderRequest.discount());
        order.setRemark(createOrderRequest.remark());
        order.setIsDeleted(false);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);


        List<OrderLine> orderLines = new ArrayList<>();

        // validation order line
        Boolean isValidOrder = createOrderRequest.orderLines().stream()
                .allMatch(orderLineDto -> {
                  Optional<Product> optionalProduct = productRepository.findByCode(orderLineDto.code());
//                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Product code has not been found"));

                    if (optionalProduct.isPresent()){
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(optionalProduct.get());
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setUnitPrice(orderLineDto.unitPrice());
                        orderLine.setOrder(order);
                        orderLines.add(orderLine);
                        return true ;
                    }

                    return false;
                });


        if (!isValidOrder){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Invalid order line");
        }


        order.setOrderLines(orderLines);

        Order savedOrder = orderRepository.save(order);


        return orderMapper.mapOrderToOrderResponse(savedOrder);
    }


    @Override
    public Page<OrderResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC , "id");
        PageRequest pageRequest = PageRequest.of(pageNumber , pageSize , sortById);
        Page<Order> orders = orderRepository.findAll(pageRequest);

        return orders.map(orderMapper::mapOrderToOrderResponse);
    }


    @Override
    public OrderResponse findById(UUID id) {

        Order orderResponse = orderRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Order not been founded")
                );

        return orderMapper.mapOrderToOrderResponse(orderResponse);
    }


    @Override
    public OrderResponse softDeleteById(UUID id) {

        Order orderResponse = orderRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Order not been founded")
                );

        orderResponse.setIsDeleted(true);
        orderRepository.save(orderResponse);

        return orderMapper.mapOrderToOrderResponse(orderResponse);

    }


    @Override
    public void deleteById(UUID id) {

        Order orderResponse = orderRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Order not been founded")
                );

        orderRepository.deleteById(id);

    }


    @Override
    public OrderResponse setPaymentById(UUID id) {

        Order orderResponse = orderRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Order not been founded")
                );

        orderResponse.setStatus(true);
        orderRepository.save(orderResponse);

        return orderMapper.mapOrderToOrderResponse(orderResponse);

    }
}
