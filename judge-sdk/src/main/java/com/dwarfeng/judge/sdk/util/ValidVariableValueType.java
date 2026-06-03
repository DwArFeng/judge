package com.dwarfeng.judge.sdk.util;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 变量值类型字段有效性验证注解。
 *
 * @author DwArFeng
 * @since 2.6.0
 */
@Documented
@Constraint(
        validatedBy = {ValidVariableValueType.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVariableValueType {

    String message() default "invalid variable value type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class InternalConstraintValidator implements ConstraintValidator<ValidVariableValueType, Integer> {

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            try {
                return Constants.variableValueTypeSpace().contains(value);
            } catch (Exception e) {
                return false;
            }
        }
    }
}
