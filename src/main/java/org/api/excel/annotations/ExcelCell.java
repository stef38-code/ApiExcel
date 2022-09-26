package org.api.excel.annotations;

import java.lang.annotation.*;

/**
 * The interface Excel cell.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCell {
    /**
     * Number int.
     *
     * @return the int
     */
    int number() default 0;

    /**
     * String format boolean.
     *
     * @return the boolean
     */
    boolean stringFormat() default false;
}
