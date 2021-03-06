package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.*;
import com.project.shoppingmall.dto.OrderInfo;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.querydsl.sql.dml.SQLUpdateClause;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {
    
    @Autowired
    private EntityManager entityManager;
    
    public ProductRepositoryImpl() {
        this(Product.class);
    }
    
    public ProductRepositoryImpl(Class<?> domainClass) {
        super(domainClass);
    }
    
    @Override
    public Page<Product> findAllProducts(String searchStr, String prdCate, Pageable pageable) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QProduct product = QProduct.product;
        QImage image = QImage.image;
        JPAQuery<Product> query = jpaQueryFactory.selectFrom(product)
                                                    .leftJoin(product.thumbImages, image).fetchJoin()
                                                    .leftJoin(product.bestSeller).fetchJoin();

        if(StringUtils.isNotBlank(searchStr)) {
            query.where(product.name.likeIgnoreCase("%"+searchStr+"%"));
        }

        if(StringUtils.isNotBlank(prdCate)) {
            query.where(product.category.name.eq(prdCate));
        }

        List<Product> products = getQuerydsl().applyPagination(pageable, query).fetch();
        long count = query.fetchCount();

        return new PageImpl<>(products, pageable, count);
    }
    
    @Override
    public List<Product> findBestSellerProductsWithLimit() {
 
        QProduct product = QProduct.product;
        QImage image = QImage.image;
        QBestSeller bestSeller = QBestSeller.bestSeller;

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        JPAQuery<Product> productJPAQuery = jpaQueryFactory.selectFrom(product)
                                                            .leftJoin(product.thumbImages, image).fetchJoin()
                                                            .innerJoin(product.bestSeller, bestSeller).fetchJoin()
                                                            .limit(8).orderBy(product.regDate.desc());

        return productJPAQuery.fetch();
    }
    
    @Override
    public List<Product> findSoldOutProducts(List<Long> productIds) {
    
        QProduct product = QProduct.product;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        
        return jpaQueryFactory.selectFrom(product).where(product.quantity.loe(0)).fetch();
    }
    
    @Override
    public long addProductQuantity(Long productId, int quantity) {
        
        QProduct product = QProduct.product;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        
        JPAUpdateClause updateClause = jpaQueryFactory.update(product)
                                                    .set(product.quantity, product.quantity.add(quantity))
                                                    .where(product.id.eq(productId));

        return updateClause.execute();
    }
}
