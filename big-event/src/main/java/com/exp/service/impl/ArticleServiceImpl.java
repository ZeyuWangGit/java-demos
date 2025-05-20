package com.exp.service.impl;

import com.exp.context.TokenContext;
import com.exp.dto.ArticleAddDTO;
import com.exp.mapper.ArticleMapper;
import com.exp.pojo.Article;
import com.exp.pojo.PageBean;
import com.exp.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(ArticleAddDTO articleAddDTO) {
        Article article = new Article();
        article.setTitle(articleAddDTO.getTitle());
        article.setContent(articleAddDTO.getContent());
        article.setCoverImg(articleAddDTO.getCoverImg());
        article.setState(articleAddDTO.getState());
        article.setCategoryId(articleAddDTO.getCategoryId());

        Map<String, Object> claims = TokenContext.get();
        Integer id = (Integer) claims.get("userId");
        article.setCreateUser(id);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String status) {
        PageBean<Article> pageBean = new PageBean<>();

        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> claims = TokenContext.get();
        Integer userId = (Integer) claims.get("userId");
        List<Article> articleList = articleMapper.list(userId, categoryId, status);
        Page<Article> page = (Page<Article>) articleList;

        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }
}
