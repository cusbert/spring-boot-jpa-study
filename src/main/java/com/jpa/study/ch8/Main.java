package com.jpa.study.ch8;

import org.aspectj.weaver.ast.Or;
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


            // printUserAndTeam(em);
    
            // beforeCascade(em);
            
            afterCascade(em);
            

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

    private static void afterCascade(EntityManager em) {
        // 영속성 전이를 사용 후
        Delivery delivery = new Delivery();
        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();

        Order order = new Order();
        order.setDelivery(delivery);

        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);

       em.persist(order); // delivery, OrderItem 플러시 시점에 영속성 전이
    }

    private static void beforeCascade(EntityManager em) {
        // 영속성 전이를 사용하기 전
        Delivery delivery = new Delivery();
        em.persist(delivery);

        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();

        em.persist(orderItem1);
        em.persist(orderItem2);

        Order order = new Order();
        order.setDelivery(delivery);
        
        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);

        em.persist(order);
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

