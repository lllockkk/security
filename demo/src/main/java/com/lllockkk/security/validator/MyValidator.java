package com.lllockkk.security.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by L on 2017/8/21.
 */
public class MyValidator implements ConstraintValidator<MyConstraint, String> {

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("init my validator");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println(value);
        return false;
    }
}
