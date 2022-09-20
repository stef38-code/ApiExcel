package org.api.excel.services;

import org.api.excel.sample.Sample;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ClassServiceTest {
    @Test
    void buildInstance() {
        ClassService<Sample> service = ClassService.clazz(Sample.class).build();
        Assertions.assertThat(service).isNotNull();
        Assertions.assertThat(service.getClazz()).isNotNull().isInstanceOf(Sample.class);
    }
    @Test
    void setField() {
        ClassService<Sample> service = ClassService.clazz(Sample.class)
                .field("firstname","John")
                .field("lastname","Doe")
                .field("age",1)
                .field("toDay", LocalDate.now())
                .build();
        Assertions.assertThat(service).isNotNull();
        Sample actual = service.getClazz();
        Assertions.assertThat(actual).isNotNull().isInstanceOf(Sample.class);
        Assertions.assertThat(actual.getFirstname()).isNotEmpty().hasToString("John");
        Assertions.assertThat(actual.getLastname()).isNotEmpty().hasToString("Doe");
        Assertions.assertThat(actual.getAge()).isEqualTo(1);
    }
}
