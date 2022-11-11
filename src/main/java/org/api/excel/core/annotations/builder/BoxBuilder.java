package org.api.excel.core.annotations.builder;

public class BoxBuilder {
    private BoxBuilder() {

    }

    public static BoxStep aNew() {
        return new StepBoxBuilder();
    }

}
