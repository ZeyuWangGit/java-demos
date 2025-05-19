package com.exp.controller;

import com.exp.dto.ArticleAddDTO;
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
}
