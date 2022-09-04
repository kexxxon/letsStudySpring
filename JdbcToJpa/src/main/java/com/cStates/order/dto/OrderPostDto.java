package com.cStates.order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class OrderPostDto {
    @Positive
    private long memberId;

    // 커피 여러개 주문
    @Valid
    private List<OrderCoffeeDto> orderCoffees;
}
