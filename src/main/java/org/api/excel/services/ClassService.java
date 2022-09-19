package org.api.excel.services;

import org.api.excel.model.CellModel;

public class ClassService<T> {
    private T clazz;
    private ClassService(Builder<T> builder){

    }
    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder<T>{
        private Builder() {
        }
        public ClassService<T> build(){
            return new ClassService(this);
        }
    }
}
