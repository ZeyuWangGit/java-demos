package com.exp.controller;

import com.exp.dto.CategoryAddDTO;
import com.exp.dto.CategoryUpdateDTO;
import com.exp.pojo.Category;
import com.exp.pojo.Result;
import com.exp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody @Validated CategoryAddDTO categoryAddDTO) {
        categoryService.add(categoryAddDTO);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list() {
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam("id") Integer id) {
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody @Validated CategoryUpdateDTO categoryUpdateDTO) {
        categoryService.update(categoryUpdateDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam("id") Integer id) {
        categoryService.delete(id);
        return Result.success();
    }
}
