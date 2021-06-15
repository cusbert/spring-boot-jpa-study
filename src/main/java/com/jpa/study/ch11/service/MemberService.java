package com.jpa.study.ch11.service;

import com.jpa.study.ch11.domain.Member;

import java.util.List;

public interface MemberService {

    Long join(Member member);

    List<Member> findMembers();

    Member findOne(Long memberId);
}
