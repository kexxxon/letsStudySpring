package com.cStates.order.mapper;

import com.cStates.coffee.dto.CoffeeResponseDto;
import com.cStates.coffee.entity.Coffee;
import com.cStates.coffee.entity.CoffeeRef;
import com.cStates.coffee.service.CoffeeService;
import com.cStates.order.dto.OrderCoffeeResponseDto;
import com.cStates.order.entity.Order;
import com.cStates.order.dto.OrderPostDto;
import com.cStates.order.dto.OrderResponseDto;
import org.mapstruct.Mapper;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        order.setMemberId(
                new AggregateReference.IdOnlyAggregateReference(orderPostDto.getMemberId()));
        Set<CoffeeRef> orderCoffees = orderPostDto.getOrderCoffees()
                .stream()
                .map(orderCoffeeDto -> new CoffeeRef(orderCoffeeDto.getCoffeeId(),
                     orderCoffeeDto.getQuantity()))
                .collect(Collectors.toSet());
        order.setOrderCoffees(orderCoffees);

        return order;
    }

    default OrderResponseDto orderToOrderResponseDto(CoffeeService coffeeService, Order order) {
        long memberId = order.getMemberId().getId();

        List<OrderCoffeeResponseDto> orderCoffees = orderToOrderCoffeeResponseDto(coffeeService, order.getOrderCoffees());

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderCoffees(orderCoffees);
        orderResponseDto.setMemberId(memberId);
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());

        return orderResponseDto;
    }

    default List<OrderCoffeeResponseDto> orderToOrderCoffeeResponseDto(CoffeeService coffeeService,
                                                                      Set<CoffeeRef> orderCoffees) {
        return orderCoffees
                .stream()
                .map(CoffeeRef -> {
                    Coffee coffee = coffeeService.findCoffee(CoffeeRef.getCoffeeId());

                    return new OrderCoffeeResponseDto(coffee.getCoffeeId(),
                            coffee.getKorName(),
                            coffee.getEngName(),
                            coffee.getPrice(),
                            CoffeeRef.getQuantity());})
                .collect(Collectors.toList());
    }
}
