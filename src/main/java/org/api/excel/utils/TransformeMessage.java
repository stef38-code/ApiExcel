package org.api.excel.utils;

import java.text.MessageFormat;

public class TransformeMessage {
    private TransformeMessage() {
        throw new UnsupportedOperationException("TransformeMessage is a utility class and cannot be instantiated");
    }

    /**
     * message args
     *
     * @param msgPattern String pattern
     * @param args       args
     * @return String
     */
    public static String transfort(String msgPattern, Object[] args) {
        MessageFormat format = new MessageFormat(msgPattern);
        return format.format(args);
    }
}
