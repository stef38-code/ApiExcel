package org.api.excel.services.commun;

import sample.Sample;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClassServiceTest {
    @Test
    void buildInstance() {
        ClassService.Builder<Sample> builder = ClassService.clazz(Sample.class);
        ClassService<Sample> service = builder.build();
        assertThat(service).isNotNull();
        assertThat(service.getClazz()).isNotNull().isInstanceOf(Sample.class);
    }

    @Test
    void newInstance_Lorsque_PrivateConstructeur_Attend_ClassServiceException() {
        ClassService.Builder<ClassService> builder = ClassService.clazz(ClassService.class);
        assertThatThrownBy(builder::build)
                .isInstanceOf(ClassServiceException.class)
                .hasMessage("Cannot create instance :ClassService");
    }

    @Test
    void setField_FielNoSettable_Exception() {
        ClassService.Builder<Sample> builder = ClassService.clazz(Sample.class);
        builder
                .field("stringValue", "John");
        assertThatThrownBy(builder::build)
                .isInstanceOf(ClassServiceException.class)
                .hasMessage("Cannot set :stringValue");
    }

    @Test
    void setField() {
        ClassService.Builder<Sample> builder = ClassService.clazz(Sample.class);
        LocalDate now = LocalDate.now();
        builder
                .field("firstname", "John")
                .field("lastname", "Doe")
                .field("age", 1)
                .field("toDay", now);
        ClassService<Sample> service = builder
                .build();
        assertThat(service).isNotNull();
        Sample actual = service.getClazz();
        assertThat(actual).isNotNull().isInstanceOf(Sample.class);
        assertThat(actual.getFirstname()).isNotEmpty().hasToString("John");
        assertThat(actual.getLastname()).isNotEmpty().hasToString("Doe");
        assertThat(actual.getAge()).isEqualTo(1);
        assertThat(actual.getToDay()).isEqualTo(now);
    }

}
