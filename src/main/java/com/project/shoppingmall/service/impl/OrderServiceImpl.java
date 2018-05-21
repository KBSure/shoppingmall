package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.*;
import com.project.shoppingmall.dto.OrderInfo;
import com.project.shoppingmall.repository.DeliveryRepository;
import com.project.shoppingmall.repository.MembersRepository;
import com.project.shoppingmall.repository.OrdersRepository;
import com.project.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrdersRepository ordersRepository;
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Autowired
    private DeliveryRepository deliveryRepository;
    
    @Transactional
    @Override
    public Order registOrder(OrderInfo orderInfo) {
    
        Member member = membersRepository.findById(orderInfo.getMemberId()).get();
        Delivery delivery = deliveryRepository.findById(1).get();
        
        Order order = makeOrder(orderInfo, member, delivery);
        
        return ordersRepository.save(order);
    }
    
    private Order makeOrder(OrderInfo orderInfo, Member member, Delivery delivery) {
        Address address = new Address(orderInfo.getPhone(), orderInfo.getAdrZipcode(), orderInfo.getAdrLocation(), orderInfo.getAdrDetail());
        BankAccount bankAccount = makeBankAccount(orderInfo);
        
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setBankAccount(bankAccount);
        order.setAddress(address);
        order.setMessage(orderInfo.getMessage());
        order.setDeliveryState(DeliveryState.ORDERED);
        order.setReceiver(orderInfo.getReceiver());
        order.setRegDate(LocalDateTime.now());
        return order;
    }
    
    private BankAccount makeBankAccount(OrderInfo orderInfo) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setDepositor(orderInfo.getDepositor());
        bankAccount.setBank(orderInfo.getBank());
        bankAccount.setAccount(orderInfo.getAccount());
        return bankAccount;
    }
    
    @Override
    public List<Order> getMemberOrders(Long memberId) {
    
        return ordersRepository.findMemberOrders(memberId);
    }
}
