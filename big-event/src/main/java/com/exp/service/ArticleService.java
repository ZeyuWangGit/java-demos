package com.exp.service;

import com.exp.dto.ArticleAddDTO;
import com.exp.pojo.Article;
import com.exp.pojo.PageBean;

public interface ArticleService {
    void add(ArticleAddDTO articleAddDTO);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String status);
}
