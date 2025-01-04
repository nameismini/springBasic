package com.core.member;

public interface MemberRepository {
    void save(Member mamber);

    Member findById(Long memberId);
}
