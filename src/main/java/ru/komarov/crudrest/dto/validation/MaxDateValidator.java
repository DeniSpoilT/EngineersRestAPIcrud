package ru.komarov.crudrest.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MaxDateValidator implements ConstraintValidator<MaxDate, LocalDate> {

    private int monthToReduce;
    @Override
    public void initialize(MaxDate constraintAnnotation) {
        this.monthToReduce = constraintAnnotation.monthToReduce();
    }


    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate maxDate = currentDate.minusMonths(monthToReduce);
            return value.isBefore(maxDate);
        } catch (Exception e) {
            return false;
        }
    }
}
