package org.api.excel.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.function.Supplier;


public class Info {
    private Info() {
        throw new UnsupportedOperationException("Debug is a utility class and cannot be instantiated");
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

    public static void print(Object cThis, String msg) {
        Log logger = LogFactory.getLog(cThis.getClass());
        logger.info(msg);
    }

    /**
     * @param cThis
     * @param message
     * @param args
     */
    private static void printArgs(Object cThis, Supplier<String> message, Object... args) {
        Log logger = LogFactory.getLog(cThis.getClass());
        String msg = TransformeMessage.transfort(message.get(), args);
        logger.info(msg);
    }

}
