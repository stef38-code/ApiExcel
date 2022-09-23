package org.api.excel.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tools.FileUtil;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ConditionsTest {


    /**
     * Method under test: {@link Conditions#requireNotEmpty(String)}
     */
    @Test
    void requireNotBlank() {
        Assertions.assertThatThrownBy(() -> Conditions.requireNotEmpty(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("String not may be blank");
    }



    /**
     * Method under test: {@link Conditions#requireNotEmpty(Collection, String)}
     */
    @Test
    void requireNotBlank4() {
        ArrayList<Object> objectList = new ArrayList<>();
        Assertions.assertThatThrownBy(() -> Conditions.requireNotEmpty(objectList, "Test unitaire"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Test unitaire");
    }

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

    /**
     * Method under test: {@link Conditions#requireFileAndExists(String)}
     */
    @Test
    void requireFileAndExists_when_fileNotExist_when_IllegalArgumentException() {
        String file = FileUtil.getAbsolutePath("sample2.txt");
        Assertions.assertThatThrownBy(() -> Conditions.requireFileAndExists(file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Is not file");
    }

    /**
     * Method under test: {@link Conditions#requireExists(File)}
     */
    @Test
    void requireExists() {
        String file = FileUtil.getAbsolutePath("sample2.txt");
        Assertions.assertThatThrownBy(() -> Conditions.requireExists(new File(file)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("File or directory not exist");
    }

    /**
     * Method under test: {@link Conditions#requireIsFile(File)}
     */
    @Test
    void requireIsFile_then_directory_when_IllegalArgumentException() {
        String directory = FileUtil.getAbsolutePath("");

        Assertions.assertThatThrownBy(() -> Conditions.requireIsFile(new File(directory)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Is not file");
    }
}

