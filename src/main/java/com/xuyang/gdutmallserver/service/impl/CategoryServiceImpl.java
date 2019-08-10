package com.xuyang.gdutmallserver.service.impl;

import com.xuyang.gdutmallserver.mapper.CategoryMapper;
import com.xuyang.gdutmallserver.model.Category;
import com.xuyang.gdutmallserver.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public int addCategory(Category model) {
        return this.categoryMapper.insert(model);
    }

    public List<Category> getCategoryList(Integer parentId) {
        return this.categoryMapper.selectCategory(parentId);
    }
}