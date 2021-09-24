package com.example.mobilelele.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.YearMonth;

public class YearPastOrPresentValidator implements ConstraintValidator<YearInPastOrPresent, Integer> {
    private int minYear;

    @Override // default initialize method
    public void initialize(YearInPastOrPresent constraintAnnotation) {
        this.minYear = constraintAnnotation.minYear();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) { // value (comes from HTML tag)
            return false; // make it required (like @NotNull)
        }
        int nowYear = YearMonth.now().getYear(); // get current year
        return value >= minYear && value <= nowYear; // validate year is in the period
    }
}
