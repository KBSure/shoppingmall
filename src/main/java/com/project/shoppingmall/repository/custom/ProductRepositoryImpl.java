package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.ImageType;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.QImage;
import com.project.shoppingmall.domain.QProduct;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;


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
        JPAQuery<Product> query = jpaQueryFactory.selectFrom(product).leftJoin(product.images, image);
        
        if(StringUtils.isNotBlank(searchStr)) {
            query.where(product.name.likeIgnoreCase("%"+searchStr+"%"));
        }
        
        if(StringUtils.isNotBlank(prdCate)) {
            query.where(product.category.name.eq(prdCate));
        }
        
        List<Product> products = getQuerydsl().applyPagination(pageable, query).fetchJoin().fetch();
        long count = query.fetchCount();
        
        return new PageImpl<>(products, pageable, count);
    }
    
    @Override
    public List<Product> findBestSellerProductsByLimit() {
    
        QProduct product = QProduct.product;
        QImage image = QImage.image;
    
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
    
        JPAQuery<Product> productJPAQuery = jpaQueryFactory.selectFrom(product)
                                                            .leftJoin(product.images, image)
                                                            .where(product.bestSeller.eq(true).and(image.type.eq(ImageType.THUMB_NAIL)))
                                                            .limit(8).orderBy(product.regDate.desc());
    
        return productJPAQuery.fetchJoin().fetch();
    }
    
    
}
