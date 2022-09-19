package org.api.excel.reflection;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ClassToolsTest {
    /**
     * Method under test: {@link ClassTools#getNewInstance(Class)}
     */
    @Test
    void getNewInstance() throws IllegalAccessException {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by getNewInstance(Class)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        ClassTools classTools = new ClassTools();
        classTools.getNewInstance(Object.class);
    }

    /**
     * Method under test: {@link ClassTools#getNewInstance(Class)}
     */
    @Test
    void getNewInstance2() throws IllegalAccessException {
        ClassTools classTools = new ClassTools();
        assertTrue(classTools.getNewInstance(ClassTools.class) instanceof ClassTools);
    }

    /**
     * Method under test: {@link ClassTools#getNewInstance(Class)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void getNewInstance3() throws IllegalAccessException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Class.getDeclaredConstructor(java.lang.Class[])" because "tclass" is null
        //       at org.api.excel.reflection.ClassTools.getNewInstance(ClassTools.java:16)
        //   In order to prevent getNewInstance(Class)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getNewInstance(Class).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ClassTools()).getNewInstance(null);
    }

    /**
     * Method under test: {@link ClassTools#setterField(Object, String, Object)}
     */
    @Test
    void setterField() throws IllegalAccessException {
        assertThrows(IllegalAccessException.class, () -> (new ClassTools()).setterField("Tclass", "Name Field", "Value"));
    }
}

