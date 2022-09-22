package org.api.excel.services;

import org.api.excel.sample.Sample;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ClassServiceTest {
    @Test
    void buildInstance() {
        ClassService<Sample> service = ClassService.clazz(Sample.class).build();
        assertThat(service).isNotNull();
        assertThat(service.getClazz()).isNotNull().isInstanceOf(Sample.class);
    }

    @Test
    void newInstance_Lorsque_PrivateContructeur_Attend_ClassServiceException() {
        Assertions.assertThatThrownBy(() -> ClassService.clazz(ClassService.class).build())
                .isInstanceOf(ClassServiceException.class)
                .hasMessage("Cannot create instance :ClassService");
    }

    @Test
    void setField_FielNoSettable_Exception() {
        Assertions.assertThatThrownBy(() ->  ClassService.clazz(Sample.class)
                .field("stringValue","John")
                .build())
                .isInstanceOf(ClassServiceException.class)
                .hasMessage("Cannot set :stringValue");
    }

    @Test
    void setField() {
        ClassService<Sample> service = ClassService.clazz(Sample.class)
                .field("firstname","John")
                .field("lastname","Doe")
                .field("age",1)
                .field("toDay", LocalDate.now())
                .build();
        assertThat(service).isNotNull();
        Sample actual = service.getClazz();
        assertThat(actual).isNotNull().isInstanceOf(Sample.class);
        assertThat(actual.getFirstname()).isNotEmpty().hasToString("John");
        assertThat(actual.getLastname()).isNotEmpty().hasToString("Doe");
        assertThat(actual.getAge()).isEqualTo(1);
    }

}
