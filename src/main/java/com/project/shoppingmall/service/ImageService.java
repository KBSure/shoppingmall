package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Image;

import java.util.List;

public interface ImageService {

    Image getImage(Long id);
    List<Image> getAllImagesByProductId(Long productId);
    
}
