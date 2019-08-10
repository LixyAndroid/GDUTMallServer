package com.xuyang.gdutmallserver.mapper;

import com.xuyang.gdutmallserver.model.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer paramInteger);

    int insert(Category paramCategory);

    int insertSelective(Category paramCategory);

    Category selectByPrimaryKey(Integer paramInteger);

    int updateByPrimaryKeySelective(Category paramCategory);

    int updateByPrimaryKey(Category paramCategory);

    List<Category> selectCategory(Integer paramInteger);
}
