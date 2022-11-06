package com.example.task_1.validation;

import com.example.task_1.entities.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class UserStatusValidator implements ConstraintValidator<UserStatusSubset, Status> {
    private Status[] subset;

    @Override
    public void initialize(UserStatusSubset constraint){
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Status value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
