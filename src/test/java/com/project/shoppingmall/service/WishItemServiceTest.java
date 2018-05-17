package com.project.shoppingmall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class WishItemServiceTest {

    @Autowired
    private WishlistService wishlistService;

    @Test
    public void wishlistService주입테스트(){
        assertNotNull(wishlistService);
    }

}
