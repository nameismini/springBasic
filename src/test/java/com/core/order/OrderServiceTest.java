package com.core.order;

import com.core.AppConfig;
import com.core.discount.FixDiscountPolicy;
import com.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OrderServiceTest {


    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder() {
        //BDD (Behavior-Driven Development)
        //given(주어진 상태): 멤버 정보를 입력
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        //when(동작시): 멤버 저장 , 오더주문
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //then(결과):
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }
/*
    @Test
    void fieldInjectionTest() {
        OrderServiceImpl orderService1 = new OrderServiceImpl();

        orderService1.setMemberRepository(new MemoryMemberRepository());
        orderService1.setDiscountPolicy(new FixDiscountPolicy());
        Member member = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(member);

        orderService1.createOrder(1L, "itemA", 10000);
    }*/
}