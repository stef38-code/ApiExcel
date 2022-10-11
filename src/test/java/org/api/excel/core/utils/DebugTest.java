package org.api.excel.core.utils;

import org.api.excel.core.utils.Debug;
import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S2699")
class DebugTest {
    /**
     * Method under test: {@link Debug#print(Object, String, Throwable)}
     */
    @Test
    void print_String() {
        Debug.print(this, "Get {0} {1} {2}", "1", "2", "3");
    }

    /**
     * Method under test: {@link Debug#print(Object, String, Throwable)}
     */
    @Test
    void print_Throwable() {
        Throwable throwable = new Throwable();
        throwable.setStackTrace(
                new StackTraceElement[]{new StackTraceElement("Declaring Class", "Method Name", "foo.txt", 2)});
        Debug.print(this, "Get", new Exception("Exception TU"));

    }

    @Test
    void print_then_args_And_Throwable() {
        Throwable throwable = new Throwable();
        throwable.setStackTrace(
                new StackTraceElement[]{new StackTraceElement("Declaring Class", "Method Name", "foo.txt", 2)});
        Debug.print(this, new Exception("Exception TU"), "Get {0} {1} {2}", "1", "2", "3");

    }

}

