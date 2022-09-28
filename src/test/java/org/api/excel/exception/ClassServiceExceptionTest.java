package org.api.excel.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassServiceExceptionTest {
    /**
     * Method under test: {@link ClassServiceException#ClassServiceException(String)}
     */
    @Test
    void constructor() {
        ClassServiceException actualClassServiceException = new ClassServiceException("An error occurred");
        assertThat(actualClassServiceException.getCause()).isNull();
        assertThat(actualClassServiceException.getSuppressed()).isEmpty();
        assertThat(actualClassServiceException.getMessage()).hasToString("An error occurred");
        assertThat(actualClassServiceException.getLocalizedMessage()).hasToString("An error occurred");
    }
}

