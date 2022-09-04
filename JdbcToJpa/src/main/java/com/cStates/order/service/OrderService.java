package com.cStates.order.service;

import com.cStates.coffee.service.CoffeeService;
import com.cStates.exception.BusinessLogicException;
import com.cStates.exception.ExceptionCode;
import com.cStates.member.service.MemberService;
import com.cStates.order.entity.Order;
import com.cStates.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private MemberService memberService;
    final private CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository,
                        MemberService memberService,
                        CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order createOrder(Order order) {
        // 1. 회원이 존재하는지 확인
        memberService.findVerifiedMember(order.getMemberId().getId());

        // 2. 커피가 존재하는지 확인
        order.getOrderCoffees()
                .stream()
                .forEach(coffeeRef -> {
                    coffeeService.findVerifiedCoffee(coffeeRef.getCoffeeId());
                });

        return orderRepository.save(order);
    }

    public Order findOrder(long orderId) {

        return findVerifiedOrder(orderId);
    }

    public List<Order> findOrders() {

        return (List<Order>) orderRepository.findAll();
    }

    // 3. 주문 정보 취소
    public void cancelOrder(long orderId) {
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        // 4. OrderStatus의 step이 2 미만일 때에만 주문 취소 가능
        if(step >= 2)
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);

        // 5. ORDER_REQUEST까지만 주문 취소 가능
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder = optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}
