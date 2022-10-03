package org.api.excel.utils;

import org.apache.commons.collections4.CollectionUtils;


import java.util.List;
import java.util.Optional;

public class Return {
    private Return() {
        throw new UnsupportedOperationException("Return is a utility class and cannot be instantiated");
    }
    public static <T> Optional<List<T>> byDefaultOptionalEmpty(List<T> values) {
        if (CollectionUtils.isEmpty(values)) {
            return Optional.empty();
        }
        return Optional.of(values);
    }
}
