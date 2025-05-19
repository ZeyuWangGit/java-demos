package com.exp.validation;

import com.exp.anno.ArticleStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ArticleStatusValidation implements ConstraintValidator<ArticleStatus, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false; // Null values are considered invalid
        }
        if(s.equals("Draft") || s.equals("Published")) {
            return true; // Valid status
        }
        return false;
    }
}
