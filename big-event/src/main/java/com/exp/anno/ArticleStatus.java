package com.exp.anno;

import com.exp.validation.ArticleStatusValidation;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ArticleStatusValidation.class})
public @interface ArticleStatus {
    String message() default "Invalid article status";
    Class<?>[] groups() default {};
    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
