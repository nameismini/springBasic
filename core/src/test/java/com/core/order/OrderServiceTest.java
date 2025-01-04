package com.core.order;

import com.core.member.Grade;
import com.core.member.Member;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

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
}