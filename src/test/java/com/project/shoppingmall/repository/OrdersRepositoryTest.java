package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrdersRepositoryTest {
    
    @Autowired
    private OrdersRepository ordersRepository;
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Test
    public void testNotNull() {
        assertNotNull(ordersRepository);
    }
    
    @Test
    public void testSaveOrder() {
        Member member = createMember();
    
        Delivery delivery = createDelivery();
    
        Order order = createOrder(member, delivery);
    
        Order saveOrder = ordersRepository.save(order);
        
        assertNotNull(saveOrder);
    }
    
    @Test
    public void testFinMemberdOrders() {
        Member member = createMember();
        Delivery delivery = createDelivery();
        Order order = createOrder(member, delivery);
        Order saveOrder = ordersRepository.save(order);
        
        List<Order> orders = ordersRepository.findMemberOrders(saveOrder.getId());
        
        assertFalse(orders.isEmpty());
    }
    
    private Order createOrder(Member member, Delivery delivery) {
        Order order = new Order();
        order.setMember(member);
        order.setAddress(createAddress());
        order.setBankAccount(createBankAccount());
        order.setDelivery(delivery);
        return order;
    }
    
    private Delivery createDelivery() {
        Delivery delivery = new Delivery();
        delivery.setCompanyName("택배회사");
        delivery.setShippingCharge(5000);
        return delivery;
    }
    
    private Member createMember() {
        Member newMember = new Member();
        Role role = new Role();
        role.setName("USER");
        newMember.addRole(role);
        return newMember;
    }
    
    
    private Address createAddress() {
        
        return new Address("010-0000-1111", "12345", "서울시", "강남구 역삼동 111");
    }
    
    private BankAccount createBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount("1234-4567-7890");
        bankAccount.setBank("은행");
        bankAccount.setDepositor("입금자");
        return bankAccount;
    }

}