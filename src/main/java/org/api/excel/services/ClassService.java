package org.api.excel.services;

import org.api.excel.reflection.ClassTools;

public class ClassService<T> {
    private final T clazz;

    private ClassService(Builder<T> builder) {
        this.clazz = builder.clazz;
    }

    public static <T> Builder clazz(Class<T> clazz) {
        return new Builder<T>().clazz(clazz);
    }

    public T getClazz() {
        return clazz;
    }

    public static final class Builder<C> {
        private C clazz;

        private Builder() {
        }

        public  Builder<C> clazz(Class<C> obj) {
            try {
                this.clazz = ClassTools.createInstance(obj);
            } catch (ReflectiveOperationException e) {
                throw new ClassServiceException(e.getMessage());
            }
            return this;
        }

        public ClassService<C> build() {
            return new ClassService<>(this);
        }

        public Builder<C> field(String name, Object value) {
            try {
                ClassTools.setterField(this.clazz, name,value);
            } catch (ReflectiveOperationException e) {
                throw new ClassServiceException(e.getMessage());
            }
            return this;
        }
    }
}
