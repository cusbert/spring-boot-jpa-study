package com.jpa.study.ch5;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;

@SpringBootApplication
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

        // 멤버1 저장
        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("sheldon");
        member1.setAge(20);
        member1.setTeam(team);
        em.persist(member1);

        // 멤버2 저장
        Member member2 = new Member();
        member2.setId("member2");
        member2.setUsername("penny");
        member2.setAge(30);
        member2.setTeam(team);
        em.persist(member2);

        System.out.println("=== after insert");
        System.out.println(member1.toString());
        System.out.println(member2.toString());
    }
}
