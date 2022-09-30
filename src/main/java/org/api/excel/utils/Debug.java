package org.api.excel.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.function.Supplier;

/**
 * Class qui permet de mettre des messages dans les traces débug
 * <br>
 * <p>
 * exemple 1 :
 * Debug.print(this, "Get {0} {1} {2}", "1","2","3");
 * <p>
 * console:
 * 13:49:39.055 [DEBUG] - Get 1 2 3
 * </p>
 * </p>
 * <br>
 * <p>
 * exemple 2 :
 * Debug.print(this, "Get", new Exception("Exception TU"));
 * <p>
 * console :
 * 13:55:09.433 [DEBUG] - Get<br>
 * java.lang.Exception: Exception TU
 * at org.api.excel.utils.DebugTest.print_Throwable(DebugTest.java:31) ~[test-classes/:?]
 * at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:?]
 * at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[?:?]
 * at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[?:?]
 * </p>
 * </p>
 * <br>
 * <p>
 * exemple 2 :
 * Debug.print(this,  new Exception("Exception TU"),"Get {0} {1} {2}", "1","2","3");
 * <p>
 * console:
 * 13:55:09.409 [DEBUG] - Get 1 2 3<br>
 * java.lang.Exception: Exception TU
 * at org.api.excel.utils.DebugTest.print_then_args_And_Throwable(DebugTest.java:39) ~[test-classes/:?]
 * at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:?]
 * at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[?:?]
 * at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[?:?]
 * at java.lang.reflect.Method.invoke(Method.java:568) ~[?:?]
 * </p>
 * </p>
 */
public class Debug {
    private Debug() {
        throw new UnsupportedOperationException("Debug is a utility class and cannot be instantiated");
    }

    /**
     * Print message avec arguments et exception
     * <p>
     * Debug.print(this,  new Exception("Exception TU"),"Get {0} {1} {2}", "1","2","3");
     * </p>
     *
     * @param clazz      the clazz
     * @param ex         the ex
     * @param msgPattern the msg pattern
     * @param args       the args
     */
    public static void print(Object clazz, Throwable ex, String msgPattern, Object... args) {
        printThrowable(clazz, () -> TransformeMessage.transfort(msgPattern, args), ex);
    }

    /**
     * Print message avec arguments
     * <p>
     * Debug.print(this,"Get {0} {1} {2}", "1","2","3");
     * </p>
     *
     * @param clazz      the clazz
     * @param msgPattern the msg pattern
     * @param args       the args
     */
    public static void print(Object clazz, String msgPattern, Object... args) {
        printArgs(clazz, () -> msgPattern, args);
    }

    /**
     * Print message sans arguments et exception
     * <p>
     * Debug.print(this,"Get",new Exception("Exception TU"));
     * </p>
     *
     * @param cThis      the c this
     * @param msgPattern the msg pattern
     * @param ex         the ex
     */
    public static void print(Object cThis, String msgPattern, Throwable ex) {
        printThrowable(cThis, () -> msgPattern, ex);
    }

    private static void printThrowable(Object cThis, Supplier<String> message, Throwable ex) {
        Log logger = LogFactory.getLog(cThis.getClass());
        if (logger.isDebugEnabled()) {
            logger.debug(message.get(), ex);
        }
    }

    /**
     * @param cThis
     * @param message
     * @param args
     */
    private static void printArgs(Object cThis, Supplier<String> message, Object... args) {
        Log logger = LogFactory.getLog(cThis.getClass());
        if (logger.isDebugEnabled()) {
            String msg = TransformeMessage.transfort(message.get(), args);
            logger.debug(msg);
        }
    }

}
