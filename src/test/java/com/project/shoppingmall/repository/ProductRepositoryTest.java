package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    
    @Autowired
    private ProductRepository repository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Test
    public void testNotNull() {
        assertNotNull(repository);
    }
    
    @Test
    public void testFindAll() {
        List<Product> products = repository.findAll();
    
        assertFalse(products.isEmpty());
    }
    
    @Test
    public void testSaveProduct() {
        List<Product> products = repository.findAll();
        int beforeSize = products.size();
    
        Product saveProduct = repository.save(createTestProduct());
        assertNotNull(saveProduct);
        
        int afterSize = repository.findAll().size();
        assertTrue(beforeSize == afterSize - 1);
    
        Product findProduct = repository.findById(saveProduct.getId()).get();
        assertNotNull(findProduct);
        
        assertTrue(saveProduct == findProduct);
    }
    
    @Test
    public void testUpdateProductQuantity() {
        Product testProduct = createTestProduct();
        Product saveProduct = repository.save(testProduct);
        Product findProduct = repository.findById(saveProduct.getId()).get();
    
        int originQuantity = findProduct.getQuantity();
        findProduct.setQuantity(originQuantity + 10);
        entityManager.flush();
    
        Product updateProduct = repository.findById(findProduct.getId()).get();
        
        assertEquals(updateProduct.getQuantity(), originQuantity + 10);
    }
    
    @Test
    public void testFindAllWithPagable() {
        List<Product> testProducts = createTestProductList(10);
        testProducts.forEach(p -> repository.save(p));
        entityManager.flush();
        
        int pageIndex = 0;
        int pageSize = 3;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        
        Page<Product> products = repository.findAll(pageable);
        
        assertEquals(pageSize, products.getContent().size());
    }
    
    @Test
    public void testDescSort() {
        List<Product> testProducts = createTestProductList(10);
        testProducts.forEach(p -> repository.save(p));
        entityManager.flush();
        
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<Product> products = repository.findAll(sort);
    
        Product first = products.get(0);
    
        Product second = products.get(1);
    
        assertTrue(first.getId() > second.getId());
    }
    
    
    private Product createTestProduct() {
        Product testProduct = new Product();
        testProduct.setBestSeller(false);
        testProduct.setContent("content!!!");
        testProduct.setHeight(30);
        testProduct.setWidth(30);
        testProduct.setName("testProduct");
        testProduct.setPrice(1000);
        testProduct.setQuantity(80);
        testProduct.setRegDate(LocalDateTime.now());
        testProduct.setShippingCharge(2500);
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