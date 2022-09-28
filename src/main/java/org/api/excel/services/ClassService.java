package org.api.excel.services;

import org.api.excel.exception.ClassServiceException;
import org.api.excel.reflection.Reflective;
import org.api.excel.utils.Debug;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Class service.
 *
 * @param <T> the type parameter
 *            <pre>{@code
 *                              ClassService.Builder<Sample> builder = ClassService.clazz(Sample.class);
 *                              builder
 *                                       .field("firstname","John")
 *                                       .field("lastname","Doe")
 *                                       .field("age",1)
 *                                       .field("toDay", LocalDate.now());
 *                               ClassService<Sample> service = builder
 *                                       .build();
 *                               assertThat(service.getClazz()).isNotNull().isInstanceOf(Sample.class);
 *                        }</pre>
 */
public class ClassService<T> {

    private final T clazz;

    private ClassService(Builder<T> builder) {
        this.clazz = builder.instance;
    }

    public static <T> Builder<T> clazz(Class<T> clazz) {
        return new Builder<T>().clazz(clazz);
    }

    public T getClazz() {
        return clazz;
    }

    public static final class Builder<C> {
        private final Map<String, Object> fields;
        private C instance;
        private Class<C> cClass;

        private Builder() {
            this.fields = new HashMap<>();
        }

        public Builder<C> clazz(Class<C> obj) {
            this.cClass = obj;
            return this;
        }

        public Builder<C> field(String name, Object value) {
            this.fields.put(name, value);
            return this;
        }

        public ClassService<C> build() {
            try {
                Debug.print(this.getClass(), () -> "Create new instance: {0}", cClass.getSimpleName());
                this.instance = Reflective.createInstance(cClass);
                for (Map.Entry<String, Object> field : fields.entrySet()) {
                    Debug.print(this.getClass(), () -> "set field: {0}, type value: {1}", field.getKey(), field.getValue().getClass().getSimpleName());
                    Reflective.setterField(this.instance, field.getKey(), field.getValue());
                }
            } catch (ReflectiveOperationException e) {
                throw new ClassServiceException(e.getMessage());
            }
            return new ClassService<>(this);
        }

    }
}
