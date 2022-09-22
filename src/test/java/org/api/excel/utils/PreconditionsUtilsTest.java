package org.api.excel.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PreconditionsUtilsTest {
    @Test
    void constructeur() throws ClassNotFoundException {
        Class<?> clazz = Class.forName(PreconditionsUtils.class.getName());
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        Assertions.assertThatThrownBy(() -> constructors[0].newInstance())
                .isInstanceOf(InvocationTargetException.class);

    }

    /**
     * Method under test: {@link PreconditionsUtils#requireNonNull(Optional, String)}
     */
    @Test
    void requireNonNull() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> PreconditionsUtils.requireNonNull(Optional.empty(), "Msg"));
    }
}

