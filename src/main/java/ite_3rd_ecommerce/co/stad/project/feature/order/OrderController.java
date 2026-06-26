package ite_3rd_ecommerce.co.stad.project.feature.order;

import ite_3rd_ecommerce.co.stad.project.feature.order.dto.CreateOrderRequest;
import ite_3rd_ecommerce.co.stad.project.feature.order.dto.OrderResponse;
import ite_3rd_ecommerce.co.stad.project.feature.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse createNew(@Valid @RequestBody CreateOrderRequest createOrderRequest){
        return orderService.createNew(createOrderRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ){
        return orderService.findAll(pageNumber ,pageSize);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse findById(@PathVariable UUID id){
        return orderService.findById(id);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse softDeleteByID(@PathVariable UUID id){
        return orderService.softDeleteById(id) ;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable UUID id){
        orderService.deleteById(id);
    }


    @PatchMapping("/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse setPaymentById(@PathVariable UUID id){
        return orderService.setPaymentById(id)  ;
    }

}
