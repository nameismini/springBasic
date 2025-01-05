package com.core;

import com.core.member.Grade;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;

import com.core.member.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        System.out.println("member.getId() = " + member.getId());

        Member findMember = memberService.findMember(member.getId());


        System.out.println("find Member = " + member.getName());
        System.out.println("new findMember = " + findMember.getName());
    }
}
