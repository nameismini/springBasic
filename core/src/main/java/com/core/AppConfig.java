package com.core;

import com.core.discount.DiscountPolicy;
import com.core.discount.FixDiscountPolicy;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;
import com.core.member.MemoryMemberRepository;
import com.core.order.OrderService;
import com.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean(name = "memberService")
    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), discountPolicy());
    }

}
