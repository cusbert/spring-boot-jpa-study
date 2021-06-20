package com.jpa.study.ch6;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

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
        Team team1 = new Team();
        team1.setName("팀1");
        em.persist(team1);

        // 멤버1 저장
        Member member1 = new Member();
        member1.setUsername("sheldon");
        member1.setTeam(team1); //  양방향 설정
        em.persist(member1);

        Order order1 = new Order();
        order1.setMember(member1);
        order1.setOrderStatus(OrderStatus.ORDER);
        order1.setOrderDate(new Date());
        em.persist(order1);

        Delivery delivery1 = new Delivery();
        delivery1.setDeliveryStatus(DeliveryStatus.READY);
        delivery1.setOrder(order1);
        em.persist(delivery1);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrder(order1);
        orderItem1.setOrderPrice(100);
        em.persist(orderItem1);

        System.out.println("=== after insert");

        System.out.println(member1.toString());
        System.out.println(order1.toString());

    }
}

