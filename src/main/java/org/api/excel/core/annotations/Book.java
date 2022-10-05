package org.api.excel.core.annotations;

import java.lang.annotation.*;

/**
 * The interface Excel workbook.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Book {
    /**
     * Value excel sheet [ ].
     *
     * @return the excel sheet [ ]
     */
    Page[] value() default {};
}
