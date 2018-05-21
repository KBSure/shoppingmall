package com.project.shoppingmall.service.impl;


import com.project.shoppingmall.domain.*;
import com.project.shoppingmall.repository.OrderItemsRepository;
import com.project.shoppingmall.repository.ProductRepository;
import com.project.shoppingmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class OrderItemServiceImpl implements OrderItemService {
    
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    
    @Transactional
    @Override
    public List<OrderItem> registOrderItems(Order order, List<Product> products, List<Integer> quantities) {
    
        List<OrderItem> orderItems = makeOrderItems(order, products, quantities);
    
        return orderItemsRepository.saveAll(orderItems);
    }
    
    private List<OrderItem> makeOrderItems(Order order, List<Product> products, List<Integer> quantities) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);
            OrderItem orderItem = makeOrderItem(order, product, quantity);
            orderItems.add(orderItem);
        }
        return orderItems;
    }
    
    private OrderItem makeOrderItem(Order order, Product product, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setProductName(product.getName());
        orderItem.setProductPrice(product.getPrice());
        orderItem.setQuantity(quantity);
        orderItem.setStart_date(LocalDateTime.now());
        OrderImage orderImage = convertOrderImage(product.getThumbImages().get(0));
        orderItem.addOrderImage(orderImage);
        return orderItem;
    }
    
    private OrderImage convertOrderImage(Image image) {
        OrderImage orderImage = new OrderImage();
        orderImage.setMimeType(image.getMimeType());
        orderImage.setName(image.getName());
        orderImage.setSize(image.getSize());
        orderImage.setType(image.getType());
        return orderImage;
    }
    
}
