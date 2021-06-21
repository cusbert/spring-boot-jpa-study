package com.jpa.study.ch12.repository;

import com.jpa.study.ch12.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);
}
    // 메소드에 JPQL 쿼리 작성
    /*
    @Query("select m from Member m where m.username =:name")
    Member findByUserName();
     */

