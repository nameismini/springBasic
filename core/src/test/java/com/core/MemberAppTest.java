package com.core;

import com.core.member.Grade;
import com.core.member.Member;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MemberAppTest {
    @Test
    void join(){
        Map<Long, Member> map = new HashMap();

        Member member1 = new Member(1L, "test1", Grade.VIP);
        Member member2 = new Member(2L, "test2", Grade.BASIC);

        map.put(member1.getId(), member1);
        map.put(member2.getId(), member2);

        System.out.println(map.get(2L));

    }


}