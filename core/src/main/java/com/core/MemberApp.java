package com.core;

import com.core.member.Grade;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;

import com.core.member.Member;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        System.out.println("member.getId() = " + member.getId());

        Member findMember = memberService.findMember(member.getId());


        System.out.println("find Member = " + member.getName());
        System.out.println("new findMember = " + findMember.getName());
    }
}
