package com.jambit.feuermoni.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jambit.feuermoni.model.FireFighterData;


public class FireFighterDataValidator implements ConstraintValidator<ValidFireFighterData, FireFighterData> {

    private static final String MSG_FMT = "{constraint.ValidFireFighterData.%s}";

    @Override
    public void initialize(ValidFireFighterData constraintAnnotation) {
        // nope
    }

    @Override
    public boolean isValid(FireFighterData value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean valid = true;
        context.disableDefaultConstraintViolation();

        if (value.getStatus() == FireFighterData.Status.OK && value.getVitalSigns() == null) {
            context.buildConstraintViolationWithTemplate(String.format(MSG_FMT, "OK.dataExpected"))
                    .addConstraintViolation();
            valid = false;
        } else if (value.getStatus() == FireFighterData.Status.NO_DATA && value.getVitalSigns() != null) {
            context.buildConstraintViolationWithTemplate(String.format(MSG_FMT, "NO_DATA.noDataExpected"))
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
