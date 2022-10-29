package org.api.excel.model;

import org.api.excel.core.annotations.Box;
import org.api.excel.core.reflection.AnnotationInClass;
import org.api.excel.model.commun.CellModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

class BoxModelTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CellModel#builder()#build()}
     * </ul>
     */
    @Test
    void build_then_noProperties_when_NullPointerException() {
        Assertions.assertThatThrownBy(CellModel.aNew().field(null)::create)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the field cannot is null");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CellModel#builder()#build()}
     * </ul>
     */
    @Test
    void build_then_fieldNotAnnotation_when_NullPointerException() throws NoSuchFieldException {
        Field actualField = MyClass.class.getField("name");
        Assertions.assertThatThrownBy(CellModel.aNew().field(actualField)::create)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the annotation cannot is null");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CellModel#builder()#build()}
     * </ul>
     */
    @Test
    void build_then_Class_when_CellModelValue() {
        Optional<List<Field>> fieldContainAnnotation = AnnotationInClass.getFieldContainAnnotation(MyClass2.class, Box.class);
        Assertions.assertThat(fieldContainAnnotation).isPresent().containsInstanceOf(List.class);
        List<Field> fields = fieldContainAnnotation.get();
        Assertions.assertThat(fields).isNotEmpty().hasSize(1);
        CellModel build = CellModel.aNew().field(fields.get(0)).create();
        Assertions.assertThat(build.getAnnotation()).isNotNull().isInstanceOf(Box.class);
        Assertions.assertThat(build.getField()).isNotNull();
    }

    private static class MyClass {
        public String name;
    }

    private static class MyClass2 {
        @Box
        public String name;
    }
}

