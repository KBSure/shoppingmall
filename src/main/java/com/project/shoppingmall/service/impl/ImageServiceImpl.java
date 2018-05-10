package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Image;
import com.project.shoppingmall.repository.ImageRepository;
import com.project.shoppingmall.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ImageServiceImpl implements ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    @Override
    public Image getImage(Long id) {
    
        return imageRepository.findById(id).get();
    }
}
