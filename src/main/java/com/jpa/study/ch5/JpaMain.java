package com.jpa.study.ch5;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

// @SpringBootApplication
public class JpaMain {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();     // 트랜잭션 시작


            testSave(em);

            System.out.println("=== before commit");
            tx.commit();
            System.out.println("=== end commit");


        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();  // 트랜잭션 롤백
        } finally {
            em.close();     // 엔티티 매니저 종료
        }

        emf.close();        //엔티티 매니저 팩토리 종료
    }

    private static void testSave(EntityManager em) {
        // 팀 저장
        Team team = new Team("team1", "팀1");
        em.persist(team);

        Team team2 = new Team("team2", "팀2");
        em.persist(team);


        // 멤버1 저장
        Member member1 = new Member("sheldon", 20);
        member1.setTeam(team); //  양방향 설정
        em.persist(member1);

        // 멤버2 저장
        Member member2 = new Member("penny", 30);
        member2.setTeam(team); //  양방향 설정
        em.persist(member2);

        System.out.println("=== after insert");
        System.out.println(member1.toString());
        System.out.println(member2.toString());

        // 팀 변경

        member1.setTeam(team2);
        List<Member> findMember = team.getMembers(); // 계속 team1 에 조회됨

        System.out.println(findMember.toString());
    }
}

