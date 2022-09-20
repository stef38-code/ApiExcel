package org.api.excel.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class ClassTools {
    private static final Logger log = LoggerFactory.getLogger(ClassTools.class);

    private ClassTools() {
        throw new UnsupportedOperationException("ClassTools is a utility class and cannot be instantiated");
    }

    public static <T> T createInstance(Class<T> tclass) throws ReflectiveOperationException {
        try {
            return tclass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            log.error("Erreur creation nouvelle instance", e);
            throw new ReflectiveOperationException("Cannot create instance :" + tclass.getSimpleName());
        }
    }

    public static <T> void setterField(T tclass, String nameField, Object value) throws ReflectiveOperationException {

        try {
            PropertyDescriptor pd = new PropertyDescriptor(nameField, tclass.getClass());
            pd.getWriteMethod().invoke(tclass, value);
        } catch (IllegalArgumentException | InvocationTargetException | IntrospectionException |
                 IllegalAccessException e) {
            log.error("Erreur setter", e);
            throw new ReflectiveOperationException("Cannot set :" + nameField);
        }
    }
}
