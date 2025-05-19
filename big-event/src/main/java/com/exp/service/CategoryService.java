package com.exp.service;

import com.exp.dto.CategoryAddDTO;
import com.exp.dto.CategoryUpdateDTO;
import com.exp.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(CategoryAddDTO categoryAddDTO);

    List<Category> list();

    Category findById(Integer id);

    void update(CategoryUpdateDTO categoryUpdateDTO);

    void delete(Integer id);
}
