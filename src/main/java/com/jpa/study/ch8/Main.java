package com.jpa.study.ch8;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();     // 트랜잭션 시작


            printUserAndTeam(em);

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

    private static void printUserAndTeam(EntityManager em) {

        System.out.println("== printUserAndTeam");

        Long memberId = 27l;
        Member member = em.find(Member.class, memberId);
        Team team = member.getTeam();

        System.out.println("member name : " + member.getUsername());
        System.out.println("team : " + team.getName());
    }

}

