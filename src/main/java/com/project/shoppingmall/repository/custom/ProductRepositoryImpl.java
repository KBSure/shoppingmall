package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.*;
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
    
//    @Override
//    public Page<Product> findAllProducts(String searchStr, String prdCate, Pageable pageable) {
//
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
//
//        QProduct product = QProduct.product;
//        QImage image = QImage.image;
//        JPAQuery<Product> query = jpaQueryFactory.selectFrom(product)
//                                                    .leftJoin(product.images, image).fetchJoin()
//                                                    .leftJoin(product.bestSeller).fetchJoin();
//                                                    .where(image.type.eq(ImageType.THUMB_NAIL));
//
//        if(StringUtils.isNotBlank(searchStr)) {
//            query.where(product.name.likeIgnoreCase("%"+searchStr+"%"));
//        }
//
//        if(StringUtils.isNotBlank(prdCate)) {
//            query.where(product.category.name.eq(prdCate));
//        }
//
//        List<Product> products = getQuerydsl().applyPagination(pageable, query).fetch();
//        long count = query.fetchCount();
//
//        return new PageImpl<>(products, pageable, count);
//    }
//
//    @Override
//    public List<Product> findBestSellerProductsByLimit() {
//
//        QProduct product = QProduct.product;
//        QImage image = QImage.image;
//        QBestSeller bestSeller = QBestSeller.bestSeller;
//
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
//
//        JPAQuery<Product> productJPAQuery = jpaQueryFactory.selectFrom(product)
////                                                            .leftJoin(product.images, image).fetchJoin()
//                                                            .innerJoin(product.bestSeller, bestSeller).fetchJoin()
//                                                            .where(image.type.eq(ImageType.THUMB_NAIL))
//                                                            .limit(8).orderBy(product.regDate.desc());
//
//        return productJPAQuery.fetch();
//    }
//
//    @Override
//    public List<Product> findAllProductsWithThumnailByProductIds(List<Long> productIds) {
//
//        QProduct product = QProduct.product;
//        QImage image = QImage.image;
//
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
//        JPAQuery<Product> productJPAQuery = jpaQueryFactory.selectFrom(product)
//                                                            .leftJoin(product.images, image)
//                                                            .where(product.id.in(productIds).and(image.type.eq(ImageType.THUMB_NAIL)))
//                                                            .fetchJoin();
//
//        return productJPAQuery.fetch();
//    }
}
