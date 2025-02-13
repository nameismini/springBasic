package com.core.order;

import com.core.annotation.MainDiscountPolicy;
import com.core.discount.DiscountPolicy;
import com.core.discount.FixDiscountPolicy;
import com.core.member.Member;
import com.core.member.MemberRepository;
import com.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderServiceImpl")
//@RequiredArgsConstructor
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

    @Autowired private DiscountPolicy rateDiscountPolicy;

    @Autowired  //생성자 하나일때 생략 가능 , 롬복 RequiredArgsConstructor 으로 대체 가능
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) { //Qualifier 를 Annotation 으로 대체 - 문자열을 대체하고 컴파일 하기 위해
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


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
