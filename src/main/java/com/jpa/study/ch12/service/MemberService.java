package com.jpa.study.ch12.service;

import com.jpa.study.ch12.domain.Member;

import java.util.List;

public interface MemberService {

    Long join(Member member);

    List<Member> findMembers();

    Member findOne(Long memberId);
}
