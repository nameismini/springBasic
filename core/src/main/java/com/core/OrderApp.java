package com.core;

import com.core.member.Grade;
import com.core.member.Member;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;
import com.core.order.Order;
import com.core.order.OrderService;
import com.core.order.OrderServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 2000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }
}
