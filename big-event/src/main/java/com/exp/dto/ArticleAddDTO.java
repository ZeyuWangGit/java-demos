package com.exp.dto;

import com.exp.anno.ArticleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ArticleAddDTO {

    @NotBlank(message = "Title must not be blank")
    @Size(min = 1, max = 10, message = "Title must be between 1 and 10 characters")
    private String title;

    @NotBlank(message = "Content must not be blank")
    private String content;

    @NotBlank(message = "Cover image must not be blank")
    @Pattern(
        regexp = "^(https?|ftp)://[^\\s]+$",
        message = "Cover image must be a valid URL"
    )
    private String coverImg;

    @ArticleStatus
    @NotBlank(message = "State must not be blank")
    private String state;

    @NotNull(message = "Category ID must not be null")
    private Integer categoryId;

    public ArticleAddDTO() {
    }

    public ArticleAddDTO(String title, String content, String coverImg, String state, Integer categoryId) {
        this.title = title;
        this.content = content;
        this.coverImg = coverImg;
        this.state = state;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}