package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Image;
import com.project.shoppingmall.repository.ImageRepository;
import com.project.shoppingmall.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    @Override
    public Image getImage(Long id) {
    
        return imageRepository.findById(id).get();
    }
    
    @Override
    public List<Image> getAllImagesByProductId(Long productId) {
        
        return imageRepository.findAllByProductId(productId);
    }
}
