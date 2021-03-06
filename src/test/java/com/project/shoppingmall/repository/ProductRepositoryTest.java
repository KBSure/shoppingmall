package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Category;
import com.project.shoppingmall.domain.Image;
import com.project.shoppingmall.domain.ImageType;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Slf4j
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Test
    public void testNotNull() {
        assertNotNull(productRepository);
    }
    
    @Test
    public void testFindAll() {
        List<Product> products = productRepository.findAll();
    
        assertFalse(products.isEmpty());
    }
    
    @Test
    public void testSaveProduct() {
        List<Product> products = productRepository.findAll();
        int beforeSize = products.size();
    
        Product saveProduct = productRepository.save(createTestProduct());
        assertNotNull(saveProduct);
        
        int afterSize = productRepository.findAll().size();
        assertTrue(beforeSize == afterSize - 1);
    
        Product findProduct = productRepository.findById(saveProduct.getId()).get();
        assertNotNull(findProduct);
        
        assertTrue(saveProduct == findProduct);
        
        assertNull(findProduct.getBestSeller());
    }
    
    @Test
    public void testUpdateProductQuantity() {
        Product testProduct = createTestProduct();
        Product saveProduct = productRepository.save(testProduct);
        Product findProduct = productRepository.findById(saveProduct.getId()).get();
    
        int originQuantity = findProduct.getQuantity();
        findProduct.setQuantity(originQuantity + 10);
        entityManager.flush();
    
        Product updateProduct = productRepository.findById(findProduct.getId()).get();
        
        assertEquals(updateProduct.getQuantity(), originQuantity + 10);
    }
    
    @Test
    public void testFindAllWithPagable() {
        List<Product> testProducts = createTestProductList(10);
        productRepository.saveAll(testProducts);
        entityManager.flush();
        
        int pageIndex = 0;
        int pageSize = 3;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        
        Page<Product> products = productRepository.findAll(pageable);
        
        assertEquals(pageSize, products.getContent().size());
    }
    
    @Test
    public void testDescSort() {
        List<Product> testProducts = createTestProductList(10);
        productRepository.saveAll(testProducts);
        
        entityManager.flush();
        
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<Product> products = productRepository.findAll(sort);
    
        Product first = products.get(0);
    
        Product second = products.get(1);
    
        assertTrue(first.getId() > second.getId());
    }
    
    @Test
    public void testFindAllProducts() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 3, sort);
        Page<Product> products = productRepository.findAllProducts("css", null, pageable);

        List<Product> productList = products.getContent();

        assertFalse(productList.isEmpty());

        productList.forEach(p -> {
            int size = p.getThumbImages().size();
            assertTrue(size == 1);
            assertEquals(ImageType.THUMB_NAIL, p.getThumbImages().get(0).getType());
        });
    }
    
    @Test
    public void testFindBestSellerProductsByLimit() {
    
        List<Product> bestSellsers = productRepository.findBestSellerProductsWithLimit();

        assertEquals(8, bestSellsers.size());
        
        bestSellsers.forEach(p -> {
            assertEquals(1, p.getThumbImages().size());
            assertEquals(ImageType.THUMB_NAIL, p.getThumbImages().get(0).getType());
            assertNotNull(p.getBestSeller());
        });
    
    }
    
    @Test
    public void testFindProductById() {
        Product product = productRepository.findById(1L).get();
    
        assertNotNull(product);
        
        assertEquals(1, product.getDetailImages().size());
        assertEquals(1, product.getThumbImages().size());
    }
    
    @Test
    public void testFindSoldoutProducts() {
        List<Product> productList = createTestProductList(5);
        productList.forEach(p -> p.setQuantity(0));
        List<Product> products = productRepository.saveAll(productList);
        List<Long> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
    
        List<Product> soldOutProducts = productRepository.findSoldOutProducts(productIds);
        
        assertEquals(productIds.size(), soldOutProducts.size());
        soldOutProducts.forEach(p -> {
            assertTrue(p.getQuantity() <= 0);
        });
    }
    
    @Test
    public void testUpdateProductsQuantity() {
        List<Product> productList = createTestProductList(5);
        List<Product> products = productRepository.saveAll(productList);
        entityManager.flush();
    
        List<Long> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
        
        int count = 0;
        for (Long productId : productIds) {
            count += productRepository.minusProductQuantity(productId, 1000);
        }
        
        entityManager.clear();
        
        assertEquals(products.size(), count);

        List<Product> findProducts = productRepository.findAllById(productIds);

        findProducts.forEach(p -> {
            assertTrue(p.getQuantity() <= 0);
        });
    }
    
    
    
    private Category createTestCategory() {
        Category category = new Category();
//        category.setId(1);
        category.setName("category");
        
        return category;
    }
    
    private Image createTestThumnailImage(Product product) {
        Image image = new Image();
        image.setName("testImg");
        image.setSize(10);
        image.setMimeType("img");
        image.setType(ImageType.THUMB_NAIL);
        image.setDetailProduct(product);
        return image;
    }
    
    private List<Image> createTestThumbnailIamges(List<Product> products) {
        List<Image> testImages = new ArrayList<>();
        for(Product product : products) {
            Image testImage = createTestThumnailImage(product);
            testImages.add(testImage);
        }
        
        return testImages;
    }
    
    private Product createTestProduct() {
        Product testProduct = new Product();
        testProduct.setContent("content!!!");
        testProduct.setHeight(30);
        testProduct.setWidth(30);
        testProduct.setName("testProduct");
        testProduct.setPrice(1000);
        testProduct.setQuantity(80);
        testProduct.setRegDate(LocalDateTime.now());
        testProduct.setCategory(createTestCategory());
        return testProduct;
    }
    
    private List<Product> createTestProductList(int listSize) {
        List<Product> list = new ArrayList<>();
        for(int i = 0; i < listSize; i++) {
            list.add(createTestProduct());
        }
        return list;
    }
    
    
}