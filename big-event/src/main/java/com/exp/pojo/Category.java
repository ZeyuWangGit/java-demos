package com.exp.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Integer id;
    private String categoryName;
    private String categoryAlias;
    private Integer createdUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
