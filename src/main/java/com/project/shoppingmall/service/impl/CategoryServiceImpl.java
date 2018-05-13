package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Category;
import com.project.shoppingmall.repository.CategoryRepository;
import com.project.shoppingmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    
    public List<Category> getCategories() {
    
        return categoryRepository.findAll();
    }
}
