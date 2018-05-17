package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Image;
import com.project.shoppingmall.domain.ImageType;
import com.project.shoppingmall.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class ImageRepositoryTest {
    
    @Autowired
    private ImageRepository repository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Test
    public void testNotNull() {
        assertNotNull(repository);
        assertNotNull(entityManager);
    }
    
    @Test
    public void testSaveImage() {
        Image testImage = createTestDetailImage();
        Image saveImage = repository.save(testImage);
        entityManager.flush();
    
        assertNotNull(saveImage);
    }
    
    @Test
    public void testFindImage() {
        Image testImage = createTestDetailImage();
        Image saveImage = repository.save(testImage);
        entityManager.flush();
    
        Image findImage = repository.findById(saveImage.getId()).get();
    
        assertEquals(saveImage, findImage);
    
        Product testProduct = testImage.getDetailProduct();
        Product findProduct = findImage.getDetailProduct();
    
        assertEquals(testProduct.getId(), findProduct.getId());
    }
    
    @Test
    public void testUpdateImage() {
        Image testImage = createTestDetailImage();
        Image saveImage = repository.save(testImage);
        entityManager.flush();
        Image findImage = repository.findById(saveImage.getId()).get();
    
        String updateName = "update";
        findImage.setName(updateName);
        entityManager.flush();
    
        Image updateImage = repository.findById(findImage.getId()).get();
        
        assertEquals(updateImage.getName(), updateName);
    }
    
    @Test
    public void testFindImages() {
//        List<Image> images = repository.findAllByProductId(1L);
    
//        assertFalse(images.isEmpty());
        
    }
    
    private Image createTestDetailImage() {
        Product product = createTestProduct();
        Image image = new Image();
        image.setDetailProduct(product);
        image.setType(ImageType.DETAIL);
        image.setMimeType("image/png");
        image.setSize(100);
        image.setName("이미지");
        return image;
    }
    
    private Image createTestThumbImage() {
        Product product = createTestProduct();
        Image image = new Image();
        image.setDetailProduct(product);
        image.setType(ImageType.THUMB_NAIL);
        image.setMimeType("image/png");
        image.setSize(100);
        image.setName("이미지");
        return image;
    }
    
    private Product createTestProduct() {
        Product product = new Product();
        product.setQuantity(10);
        product.setRegDate(LocalDateTime.now());
        product.setPrice(500);
        product.setName("상품");
        product.setWidth(10);
        product.setHeight(10);
        product.setContent("내용!!!");
        return product;
    }
    
}