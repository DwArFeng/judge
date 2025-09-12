package com.dwarfeng.judge.sdk.util;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Objects;

/**
 * 阈值字段有效性验证注解，校验取值是否位于 [0, 1] 区间。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Documented
@Constraint(
        validatedBy = {ValidThreshold.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidThreshold {

    String message() default "threshold must be in [0, 1]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 内部校验器实现。
     */
    class InternalConstraintValidator implements ConstraintValidator<ValidThreshold, Double> {

        @Override
        public boolean isValid(Double value, ConstraintValidatorContext context) {
            // 按照 Bean Validation 规范：值为 null 由 @NotNull 负责，这里视为通过。
            if (Objects.isNull(value)) {
                return true;
            }
            return value >= 0.0d && value <= 1.0d;
        }
    }
}
