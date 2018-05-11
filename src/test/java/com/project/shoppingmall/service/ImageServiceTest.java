package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Image;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.repository.ImageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@Transactional
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {
    
    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private ImageService imageService;
    
    @Test
    public void testNotNull() {
        assertNotNull(imageService);
    }
    
    @Test
    public void testGetImage() {
        Image testImage = createTestImage();
        Image saveImage = imageRepository.save(testImage);
    
        Image image = imageService.getImage(saveImage.getId());
        
        assertEquals(saveImage.getId(), image.getId());
    }
    
    private Image createTestImage() {
        Product product = createTestProduct();
        Image image = new Image();
        image.setProduct(product);
        image.setMimeType("image/png");
        image.setSize(1000);
        image.setName("이미지");
        return image;
    }
    
    private Product createTestProduct() {
        Product product = new Product();
        product.setQuantity(100);
        product.setShippingCharge(100);
        product.setRegDate(LocalDateTime.now());
        product.setPrice(500);
        product.setName("상품");
        product.setWidth(10);
        product.setHeight(10);
        product.setContent("내용!!!");
        return product;
    }
    
}