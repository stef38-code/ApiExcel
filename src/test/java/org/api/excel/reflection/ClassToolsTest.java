package org.api.excel.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ClassToolsTest {
    /**
     * Method under test: {@link ClassTools#newInstance(Class)}
     */
    @Test
    void newInstance_then_ClassTools_when_ClassToolsInstance() throws IllegalAccessException {
        ClassTools instance = ClassTools.newInstance(ClassTools.class);
        assertThat(instance).isNotNull().isInstanceOf(ClassTools.class);
    }
    /**
     * Method under test: {@link ClassTools#newInstance(Class)}
     */
    @Test
    void newInstance_then_DefaultClass_when_DefaultClassInstance() throws IllegalAccessException {
        DefaultClassTest instance = ClassTools.newInstance(DefaultClassTest.class);
        assertThat(instance).isNotNull().isInstanceOf(DefaultClassTest.class);
    }

    /**
     * Method under test: {@link ClassTools#setterField(Object, String, Object)}
     */
    @Test
    void setterField_then_stringObject_when_IllegalAccessException() {
        Assertions.assertThatThrownBy(() -> (new ClassTools()).setterField("Tclass", "Name Field", "Value"))
                .isInstanceOf(IllegalAccessException.class)
                .hasMessageContaining("Cannot set");
    }
    /**
     * Method under test: {@link ClassTools#setterField(Object, String, Object)}
     */
    @Test
    void setterField_then_DefaultClassTest_when_NameChanged() throws IllegalAccessException {
        DefaultClassTest defaultClass = new DefaultClassTest();
        ClassTools.setterField(defaultClass, "name", "Doe");
        assertThat(defaultClass.getName()).hasToString("Doe");
    }
}

