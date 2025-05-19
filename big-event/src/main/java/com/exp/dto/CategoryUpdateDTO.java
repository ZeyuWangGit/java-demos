package com.exp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryUpdateDTO {

    @NotNull(message = "ID must not be null")
    private Integer id;

    @NotBlank(message = "Category name must not be blank")
    private String categoryName;

    @NotBlank(message = "Category alias must not be blank")
    private String categoryAlias;

    public CategoryUpdateDTO() {
    }

    public CategoryUpdateDTO(Integer id, String categoryName, String categoryAlias) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryAlias = categoryAlias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias;
    }
}