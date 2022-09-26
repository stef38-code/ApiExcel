package org.api.excel.annotations;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

/**
 * The interface Excel sheet.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {
    /**
     * Number int.
     *
     * @return the int
     */
    int number() default 0;

    /**
     * Name string.
     *
     * @return the string
     */
    String name() default StringUtils.EMPTY;

    /**
     * Row number int.
     *
     * @return the int
     */
    int rowNumber() default 1;
}
