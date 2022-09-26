package org.api.excel.services;

import org.api.excel.reflection.Reflective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Class service.
 *
 * @param <T> the type parameter
 *  <pre>{@code
 *        ClassService.Builder<Sample> builder = ClassService.clazz(Sample.class);
 *        builder
 *                 .field("firstname","John")
 *                 .field("lastname","Doe")
 *                 .field("age",1)
 *                 .field("toDay", LocalDate.now());
 *         ClassService<Sample> service = builder
 *                 .build();
 *         assertThat(service.getClazz()).isNotNull().isInstanceOf(Sample.class);
 *  }</pre>
 */
public class ClassService<T> {
    private static final Logger log = LoggerFactory.getLogger(ClassService.class);
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
        private C instance;
        private Class<C> cClass;
        private final Map<String, Object> fields;
        private Builder() {
            this.fields = new HashMap<>();
        }

        public  Builder<C> clazz(Class<C> obj) {
            this.cClass = obj;
            return this;
        }
        public Builder<C> field(String name, Object value) {
            this.fields.put(name,value);
            return this;
        }

        public ClassService<C> build() {
            try {
                log.debug("Create new instance: {}",cClass.getSimpleName());
                this.instance = Reflective.createInstance(cClass);
                for (Map.Entry<String,Object> field: fields.entrySet()) {
                    log.debug("set field: {}, type value: {}", field.getKey(), field.getValue().getClass().getSimpleName());
                    Reflective.setterField(this.instance, field.getKey(),field.getValue());
                }
            } catch (ReflectiveOperationException e) {
                throw new ClassServiceException(e.getMessage());
            }
            return new ClassService<>(this);
        }

    }
}
