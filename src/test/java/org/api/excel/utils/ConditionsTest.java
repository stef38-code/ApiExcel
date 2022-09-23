package org.api.excel.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ConditionsTest {
    @Test
    void constructeur() throws ClassNotFoundException {
        Class<?> clazz = Class.forName(Conditions.class.getName());
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        Assertions.assertThatThrownBy(() -> constructors[0].newInstance())
                .isInstanceOf(InvocationTargetException.class);

    }

    /**
     * Method under test: {@link Conditions#requireNonNull(Optional, String)}
     */
    @Test
    void requireNonNull() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Conditions.requireNonNull(Optional.empty(), "Msg"));
    }
}

