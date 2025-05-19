package com.exp.service.impl;

import com.exp.context.TokenContext;
import com.exp.dto.CategoryAddDTO;
import com.exp.dto.CategoryUpdateDTO;
import com.exp.mapper.CategoryMapper;
import com.exp.pojo.Category;
import com.exp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(CategoryAddDTO categoryAddDTO) {
        Map<String, Object> claims = TokenContext.get();
        Integer id = (Integer) claims.get("userId");
        Category category = new Category();
        category.setCategoryName(categoryAddDTO.getCategoryName());
        category.setCategoryAlias(categoryAddDTO.getCategoryAlias());
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> claims = TokenContext.get();
        Integer id = (Integer) claims.get("userId");
        return categoryMapper.listBuUserId(id);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findByCategoryId(id);
    }

    @Override
    public void update(CategoryUpdateDTO categoryUpdateDTO) {
        Category category = new Category();
        category.setCategoryName(categoryUpdateDTO.getCategoryName());
        category.setCategoryAlias(categoryUpdateDTO.getCategoryAlias());
        category.setId(categoryUpdateDTO.getId());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByCategoryId(id);
    }
}
