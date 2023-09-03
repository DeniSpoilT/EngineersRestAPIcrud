package ru.komarov.crudrest.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinDateValidator.class)
public @interface MinDate {

    String message() default "Invalid date";
    int monthToReduce() default 1;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
