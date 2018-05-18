package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.Image;

import java.util.List;

public interface ImageRepositoryCustom {
    
    List<Image> findDetailImagesByProductId(Long productId);
    
    List<Image> findThumbnailImagesByProductId(Long productId);
    
}
