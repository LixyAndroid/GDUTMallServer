package com.xuyang.gdutmallserver.service;

import com.xuyang.gdutmallserver.model.Category;

import java.util.List;

public interface CategoryService {
    int addCategory(Category paramCategory);

    List<Category> getCategoryList(Integer paramInteger);
}
