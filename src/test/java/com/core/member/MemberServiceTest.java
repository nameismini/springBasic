package com.core.member;


import com.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join() {

        //BDD (Behavior-Driven Development)
        //given(주어진 상태): 멤버 자료 입력
        Member member = new Member(1L, "memberA", Grade.BASIC);

        //when(동작시): 멤버 저장 , 멤버 조회
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then(결과): ex.사용자가 로그인되었음을 확인해야 한다
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}