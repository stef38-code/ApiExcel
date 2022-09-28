package org.api.excel.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class Debug {
    private Debug() {
        throw new UnsupportedOperationException("Debug is a utility class and cannot be instantiated");
    }

    public static void print(Class<?> clazz, Supplier<String> message, Throwable ex) {
        Log logger = LogFactory.getLog(clazz.getClass());
        if (logger.isDebugEnabled()) {
            logger.debug(message.get(), ex);
        }
    }

    public static void print(Class<?> clazz, Supplier<String> message, Object... args) {
        Log logger = LogFactory.getLog(clazz.getClass());
        if (logger.isDebugEnabled()) {
            String msg = transfort(message.get(), args);
            logger.debug(msg);
        }
    }

    private static String transfort(String msgPattern, Object[] args) {
        MessageFormat format = new MessageFormat(msgPattern);
        return format.format(args);
    }
}
