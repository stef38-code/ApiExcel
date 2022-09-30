package org.api.excel.annotations;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

/**
 * The interface Excel cell.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Box {//book,page//box
    /**
     * Number int.
     *
     * @return the int
     */
    int number() default 0;
    String name() default StringUtils.EMPTY;
    /**
     * String format boolean.
     *
     * @return the boolean
     */
    boolean stringFormat() default false;
}