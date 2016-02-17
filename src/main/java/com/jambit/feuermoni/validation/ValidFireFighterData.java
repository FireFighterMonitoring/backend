package com.jambit.feuermoni.validation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FireFighterDataValidator.class)
@Documented
public @interface ValidFireFighterData {

    String message() default "{constraint.ValidFireFighterData.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
