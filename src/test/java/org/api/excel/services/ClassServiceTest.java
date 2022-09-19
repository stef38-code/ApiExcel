package org.api.excel.services;

import org.api.excel.reflection.DefaultClassTest;
import org.junit.jupiter.api.Test;

class ClassServiceTest {
    @Test
    void essai() {
        ClassService<DefaultClassTest> v = ClassService.builder().build();
    }
}
