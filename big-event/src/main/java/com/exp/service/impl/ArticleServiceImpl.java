package com.exp.service.impl;

import com.exp.context.TokenContext;
import com.exp.dto.ArticleAddDTO;
import com.exp.mapper.ArticleMapper;
import com.exp.pojo.Article;
import com.exp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
