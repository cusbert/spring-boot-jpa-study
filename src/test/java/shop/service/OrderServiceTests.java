package shop.service;

import com.jpa.study.ch11.JpaStudyApplication;
import com.jpa.study.ch11.domain.*;
import com.jpa.study.ch11.exception.NotEnoughStockException;
import com.jpa.study.ch11.repository.OrderRepository;
import com.jpa.study.ch11.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JpaStudyApplication.class)
public class OrderServiceTests {

    // 주문 성공 해야함
    // 주문 시 재고 수량 초과 하면 안됨
    // 주문 취소 성공

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    @DisplayName("상품 주문 성공")
    public void success_order_item() throws Exception {

        int stockQuantity = 10;
        int orderItemPrice = 9900;
        // Given
        Member member = createMember();
        Item item = createBook("JPA Book", orderItemPrice, stockQuantity);
        int orderCount = 2;

        // When
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // Then
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getOrderStatus());
        Assertions.assertEquals(1, getOrder.getOrderItems().size()); // 주문한 상품 종류 수
        Assertions.assertEquals(orderItemPrice * orderCount, getOrder.getTotalPrice());
        Assertions.assertEquals(stockQuantity - orderCount, item.getStockQuantity());
    }

    @Test
    @Transactional
    @DisplayName("상품주문 실패 - 재고수량초과")
    public void fail_order_stock_quantity_over() throws Exception {

        int stockQuantity = 5;
        int orderItemPrice = 9900;
        // Given
        Member member = createMember();
        Item item = createBook("JPA Book", orderItemPrice, stockQuantity);
        int orderCount = 10;

        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        });
    }

    @Test
    @Transactional
    @DisplayName("주문 취소")
    public void success_order_cancel() throws Exception {

        int stockQuantity = 10;
        int orderItemPrice = 9900;
        // Given
        Member member = createMember();
        Item item = createBook("JPA Book", orderItemPrice, stockQuantity);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //When
        orderService.cancelOrder(orderId);

        //Then
        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.CANCEL, getOrder.getOrderStatus());
        Assertions.assertEquals(stockQuantity, item.getStockQuantity()); // 취소한 만큼 수량 복구
    }

    private Member createMember() {
        Member member = new Member();
        member.setUsername("Chris pine");
        member.setAddress(new Address("seoul", "city", "123-123"));
        em.persist(member);

        return member;
    }

    private Item createBook(String title, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(title);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);

        em.persist(book);
        return book;
    }
}
