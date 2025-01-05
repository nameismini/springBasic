package com.core.discount;

import com.core.member.Grade;
import com.core.member.Member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인 적용이 되어야 한다")
    void vip확인() {
        //BDD (Behavior-Driven Development)
        //given(주어진 상태): ex.유효한 계정으로 사용자가 로그인한 상태에서
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when(동작시): ex.사용자가 로그인 버튼을 클릭했을 때
        int discount = discountPolicy.discount(member, 10000);

        //then(결과): ex.사용자가 로그인되었음을 확인해야 한다
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아닌경우는 할인 적용 불가")
    void 일반고객() {
        //BDD (Behavior-Driven Development)
        //given(주어진 상태): ex.유효한 계정으로 사용자가 로그인한 상태에서
        Member member = new Member(2L, "memberB", Grade.BASIC);

        //when(동작시): ex.사용자가 로그인 버튼을 클릭했을 때
        int discount = discountPolicy.discount(member, 10000);

        //then(결과): ex.사용자가 로그인되었음을 확인해야 한다
        assertThat(discount).isEqualTo(0);
    }

}