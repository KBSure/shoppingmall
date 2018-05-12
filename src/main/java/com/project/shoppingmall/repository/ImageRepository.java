package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaQueryDslPredicateRepository<Image, Long> {
    
    @Query("select image from Image image where image.product.id =:productId")
    List<Image> findAllByProductId(@Param("productId") Long productId);

}
