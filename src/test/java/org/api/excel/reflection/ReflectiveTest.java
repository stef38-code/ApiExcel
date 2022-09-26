package org.api.excel.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectiveTest {
    /**
     * Method under test: {@link Reflective#createInstance(Class)}
     */
    @Test
    void newInstance_then_NotClass_when_IllegalAccessException() {
        Assertions.assertThatThrownBy(() -> Reflective.createInstance(Reflective.class))
                .isInstanceOf(ReflectiveOperationException.class)
                .hasMessageContaining("Cannot create instance :")
                .hasMessageContaining(Reflective.class.getSimpleName());
    }
    /**
     * Method under test: {@link Reflective#createInstance(Class)}
     */
    @Test
    void newInstance_then_ClassTools_when_ClassToolsInstance() throws ReflectiveOperationException {
        String instance = Reflective.createInstance(String.class);
        assertThat(instance).isNotNull().isInstanceOf(String.class);
    }
    /**
     * Method under test: {@link Reflective#createInstance(Class)}
     */
    @Test
    void newInstance_then_DefaultClass_when_DefaultClassInstance() throws ReflectiveOperationException {
        DefaultClass instance = Reflective.createInstance(DefaultClass.class);
        assertThat(instance).isNotNull().isInstanceOf(DefaultClass.class);
    }

    /**
     * Method under test: {@link Reflective#setterField(Object, String, Object)}
     */
    @Test
    void setterField_then_stringObject_when_ReflectiveOperationException() {
        Assertions.assertThatThrownBy(() -> Reflective.setterField("Tclass", "NameField", "Value"))
                .isInstanceOf(ReflectiveOperationException.class)
                .hasMessageContaining("Cannot set");
    }
    /**
     * Method under test: {@link Reflective#setterField(Object, String, Object)}
     */
    @Test
    void setterField_then_DefaultClassTest_when_NameChanged() throws ReflectiveOperationException {
        DefaultClass defaultClass = new DefaultClass();
        Reflective.setterField(defaultClass, "name", "Doe");
        assertThat(defaultClass.getName()).hasToString("Doe");
    }
}

