package com.exp.controller;

import com.exp.dto.ArticleAddDTO;
import com.exp.pojo.Article;
import com.exp.pojo.PageBean;
import com.exp.pojo.Result;
import com.exp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated ArticleAddDTO articleAddDTO) {
        articleService.add(articleAddDTO);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize, @RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String status) {
        PageBean<Article> articles = articleService.list(pageNum, pageSize, categoryId, status);
        return Result.success(articles);
    }
}
