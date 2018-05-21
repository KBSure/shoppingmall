package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.*;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderItemsRepositoryTest {
    
    @Autowired
    private OrdersRepository ordersRepository;
    
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    
    @Test
    public void testNotNull() {
        assertNotNull(orderItemsRepository);
    }
    
    @Test
    public void testFindAllOrderItems() {
        Order order = ordersRepository.findById(2L).get();
    
        List<OrderItem> orderItems = orderItemsRepository.findAllOrderItems(order.getId());
        
        assertFalse(orderItems.isEmpty());
    }
    
    @Test
    public void testSaveOrderItems() {
        List<OrderItem> orderItems = createOrderItems(5);
    
        List<OrderItem> saveItems = orderItemsRepository.saveAll(orderItems);
        
        assertFalse(saveItems.isEmpty());
    }
    
    private List<OrderItem> createOrderItems(int size) {
        List<OrderItem> orderItems = new ArrayList<>();
        Order order = createOrder(createMember(), createDelivery());
        for (int i = 0; i < size; i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(new Product());
            orderItem.addOrderImage(new OrderImage());
            orderItems.add(orderItem);
        }
        
        return orderItems;
    }
    
    private Order createOrder(Member member, Delivery delivery) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setAddress(createAddress());
        order.setBankAccount(createBankAccount());
        return order;
    }
    
    private Delivery createDelivery() {
        Delivery delivery = new Delivery();
        delivery.setCompanyName("택배회사");
        delivery.setShippingCharge(2500);
        return delivery;
    }
    
    private Member createMember() {
        Member newMember = new Member();
        newMember.addRole(createRole());
        return newMember;
    }
    
    private Role createRole() {
        Role role = new Role();
        role.setName("USER");
        return role;
    }
    
    
    private Address createAddress() {
        
        return new Address("010-0000-1111", "12345", "서울시", "강남구 역삼동 111");
    }
    
    private BankAccount createBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount("1234-4567-78900");
        bankAccount.setBank("은행");
        bankAccount.setDepositor("입금자");
        return bankAccount;
    }

}