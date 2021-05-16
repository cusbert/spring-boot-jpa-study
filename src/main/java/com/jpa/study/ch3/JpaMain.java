package com.jpa.study.ch3;

import com.jpa.study.ch2.Member;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class JpaMain {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();
        EntityTransaction tx2 = em.getTransaction();

        try {
            tx.begin();     // 트랜잭션 시작

            System.out.println("========== start");

            // 1. 비영속 : 객체 생성
            Member memberA = new Member();
            memberA.setId("id1");
            memberA.setUsername("Sheldon");
            memberA.setAge(20);

            Member memberB = new Member();
            memberB.setId("id2");
            memberB.setUsername("Sheldon");
            memberB.setAge(20);

            // 2. 영속 : 객체 저장
            em.persist(memberA);
            em.persist(memberB);
            // 1차 캐시에 엔티티 저장
            // 아직까지 sql 을 데이터 베이스에 보내지 않음

            System.out.println("========== end");
            // commit 하는 순간 insert sql 을 보낸다
            // tx.commit();    // 트랜잭션 커밋

            System.out.println("=== before update");
            System.out.println(memberA.toString());


            // 3. 객체 조회
            // 1차 캐시에서 조회 하고 없으면 DB 에서 조회
            // -> 조회한 엔티티를 1차 캐시에 저장
            // -> 엔티티 반환
            Member findA = em.find(Member.class, "id1");

            // 4. 객체 수정 : 변경 감지
            findA.setUsername("hi");
            findA.setAge(11);

            System.out.println("=== after update");
            System.out.println(findA.toString());

            // 5. 객체 삭제
            em.remove(findA); // 엔티티 삭제

            // 준영속성 상태 : 회원 엔티티를 영속성 컨테스트에서 분리
            // em.detach(memberB);
            // em.clear();
            // em.close();

            // 플러시 : 영속성 컨텍스트 변경 내용을 DB에 반영
            em.setFlushMode(FlushModeType.AUTO);

            tx.commit();    // 트랜잭션 커밋 => commit 하는 순간 insert sql 을 보낸다
            

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();  // 트랜잭션 롤백
        } finally {
            em.close();     // 엔티티 매니저 종료
        }

        emf.close();        //엔티티 매니저 팩토리 종료
    }
}
