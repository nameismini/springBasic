package com.core.order;

import com.core.discount.DiscountPolicy;
import com.core.discount.FixDiscountPolicy;
import com.core.member.Member;
import com.core.member.MemberRepository;
import com.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

    // 생성자 외에는 final을 사용불가
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


/*    @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("setMemberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("setDiscountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

//    @Autowired  //생성자 하나일때 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired
    /*public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("init memberRepository = " + memberRepository);
        System.out.println("init discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
