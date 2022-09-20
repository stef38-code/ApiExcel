package org.api.excel.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassToolsTest {
    /**
     * Method under test: {@link ClassTools#createInstance(Class)}
     */
    @Test
    void newInstance_then_NotClass_when_IllegalAccessException() {
        Assertions.assertThatThrownBy(() -> ClassTools.createInstance(ClassTools.class))
                .isInstanceOf(ReflectiveOperationException.class)
                .hasMessage("Cannot create instance :ClassTools");
    }
    /**
     * Method under test: {@link ClassTools#createInstance(Class)}
     */
    @Test
    void newInstance_then_ClassTools_when_ClassToolsInstance() throws ReflectiveOperationException {
        String instance = ClassTools.createInstance(String.class);
        assertThat(instance).isNotNull().isInstanceOf(String.class);
    }
    /**
     * Method under test: {@link ClassTools#createInstance(Class)}
     */
    @Test
    void newInstance_then_DefaultClass_when_DefaultClassInstance() throws ReflectiveOperationException {
        DefaultClassTest instance = ClassTools.createInstance(DefaultClassTest.class);
        assertThat(instance).isNotNull().isInstanceOf(DefaultClassTest.class);
    }

    /**
     * Method under test: {@link ClassTools#setterField(Object, String, Object)}
     */
    @Test
    void setterField_then_stringObject_when_IllegalAccessException() {
        Assertions.assertThatThrownBy(() -> ClassTools.setterField("Tclass", "Name Field", "Value"))
                .isInstanceOf(IllegalAccessException.class)
                .hasMessageContaining("Cannot set");
    }
    /**
     * Method under test: {@link ClassTools#setterField(Object, String, Object)}
     */
    @Test
    void setterField_then_DefaultClassTest_when_NameChanged() throws ReflectiveOperationException {
        DefaultClassTest defaultClass = new DefaultClassTest();
        ClassTools.setterField(defaultClass, "name", "Doe");
        assertThat(defaultClass.getName()).hasToString("Doe");
    }
}

