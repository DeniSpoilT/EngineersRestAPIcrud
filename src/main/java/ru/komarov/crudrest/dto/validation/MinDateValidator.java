package ru.komarov.crudrest.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MinDateValidator implements ConstraintValidator<MinDate, LocalDate> {

    private int monthToReduce;

    @Override
    public void initialize(MinDate constraintAnnotation) {
        this.monthToReduce = constraintAnnotation.monthToReduce();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = currentDate.minusMonths(monthToReduce);
        return value.isAfter(minDate) || value.isEqual(minDate);
    }
}
