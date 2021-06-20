package com.jpa.study.ch12.service.impl;

import com.jpa.study.ch12.domain.Member;
import com.jpa.study.ch12.repository.MemberRepository;
import com.jpa.study.ch12.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service // <contest:component-scan> 에 의해 스프링 빈 등록됨
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    /* 회원 가입 */
    @Override
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getUsername());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /* 전체 회원 조회 */
    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
