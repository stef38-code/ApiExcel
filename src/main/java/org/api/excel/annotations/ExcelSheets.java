package org.api.excel.annotations;

import java.lang.annotation.*;

/**
 * The interface Excel sheet.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheets {
    /**
     * Value excel sheet [ ].
     *
     * @return the excel sheet [ ]
     */
    ExcelSheet[] value() default {};
}
