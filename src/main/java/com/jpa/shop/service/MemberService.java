package com.jpa.shop.service;

import com.jpa.shop.domain.Member;

import java.util.List;

public interface MemberService {

    Long join(Member member);

    List<Member> findMembers();

    Member findOne(Long memberId);
}
