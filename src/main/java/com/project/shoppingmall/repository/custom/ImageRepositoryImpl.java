package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class ImageRepositoryImpl implements ImageRepositoryCustom {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Image> findDetailImagesByProductId(Long productId) {
        
        
        
        return null;
    }
    
    @Override
    public List<Image> findThumbnailImagesByProductId(Long productId) {
        return null;
    }
}
