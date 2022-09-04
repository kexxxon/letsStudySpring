package com.cStates.order.controller;

import com.cStates.coffee.entity.Coffee;
import com.cStates.coffee.service.CoffeeService;
import com.cStates.order.entity.Order;
import com.cStates.order.service.OrderService;
import com.cStates.order.dto.OrderPostDto;
import com.cStates.order.dto.OrderResponseDto;
import com.cStates.order.mapper.OrderMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v10/orders")
@Validated
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final CoffeeService coffeeService;

    public OrderController(OrderService orderService,
                           OrderMapper mapper,
                           CoffeeService coffeeService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));

        // 1. 주문한 커피 정보 가져오기
        return new ResponseEntity<>(mapper.orderToOrderResponseDto(coffeeService, order), HttpStatus.CREATED);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId){
        Order order = orderService.findOrder(orderId);

        // 2. 주문한 커피 정보 가져오기
        return new ResponseEntity<>(mapper.orderToOrderResponseDto(coffeeService, order), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> orders = orderService.findOrders();

        // 3. 주문한 커피 정보 가져오기
        List<OrderResponseDto> response = orders
                .stream()
                .map(order ->
                        mapper.orderToOrderResponseDto(coffeeService, order))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId){
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
