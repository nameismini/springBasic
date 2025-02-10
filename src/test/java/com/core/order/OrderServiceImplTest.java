package com.core.order;

import com.core.discount.FixDiscountPolicy;
import com.core.discount.RateDiscountPolicy;
import com.core.member.Grade;
import com.core.member.Member;
import com.core.member.MemberRepository;
import com.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired OrderServiceImpl orderService;



    @Test
    void createOrder(){




        // 생성자로 빈을 만들지 않았을때는 ... null
        // set 해줘야함

        MemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));


//        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
//        OrderServiceImpl orderService = new OrderServiceImpl();
        Order order = orderService.createOrder(1L, "itemA", 10000);


        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }


}