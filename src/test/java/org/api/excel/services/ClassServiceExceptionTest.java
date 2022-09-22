package org.api.excel.services;

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
        assertThat(actualClassServiceException.getSuppressed().length).isEqualTo(0);
        assertThat(actualClassServiceException.getMessage()).hasToString("An error occurred");
        assertThat(actualClassServiceException.getLocalizedMessage()).hasToString("An error occurred");
    }
}

