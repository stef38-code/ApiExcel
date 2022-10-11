package org.api.excel.core.utils;

import org.api.excel.core.utils.Conditions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.FileUtil;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
        Optional<Object> empty = Optional.empty();
        Assertions.assertThatThrownBy(() -> Conditions.requireNonNull(empty, "Msg"))
                .isInstanceOf(IllegalArgumentException.class);

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
        String path = FileUtil.getAbsolutePath("sample2.txt");
        File file = new File(path);
        Assertions.assertThatThrownBy(() -> Conditions.requireExists(file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("File or directory not exist");
    }

    /**
     * Method under test: {@link Conditions#requireIsFile(File)}
     */
    @Test
    void requireIsFile_then_directory_when_IllegalArgumentException() {
        String directory = FileUtil.getAbsolutePath("");
        File file = new File(directory);

        Assertions.assertThatThrownBy(() -> Conditions.requireIsFile(file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Is not file");
    }
}

