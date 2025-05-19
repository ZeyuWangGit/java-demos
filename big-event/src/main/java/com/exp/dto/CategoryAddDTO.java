package com.exp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CategoryAddDTO {

    @NotBlank(message = "Category name must not be blank")
    @NotEmpty
    private String categoryName;

    @NotBlank(message = "Category alias must not be blank")
    @NotEmpty
    private String categoryAlias;

    public CategoryAddDTO() {
    }

    public CategoryAddDTO(String categoryName, String categoryAlias) {
        this.categoryName = categoryName;
        this.categoryAlias = categoryAlias;
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