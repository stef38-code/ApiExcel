package org.api.excel.core.reflection;

import org.api.excel.core.utils.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * The type Class tools.
 */
public class Reflective {
    private static final Logger log = LoggerFactory.getLogger(Reflective.class);

    private Reflective() {
        throw new UnsupportedOperationException("Reflective is a utility class and cannot be instantiated");
    }

    /**
     * Create instance t.
     *
     * @param <T>    the type parameter
     * @param tclass the tclass
     * @return the t
     * @throws ReflectiveOperationException the reflective operation exception
     */
    public static <T> T createInstance(Class<T> tclass) throws ReflectiveOperationException {
        try {
            return tclass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            log.error("Erreur creation nouvelle instance", e);
            throw new ReflectiveOperationException("Cannot create instance :" + tclass.getSimpleName());
        }
    }

    /**
     * Sets field.
     *
     * @param <T>       the type parameter
     * @param tclass    the tclass
     * @param nameField the name field
     * @param value     the value
     * @throws ReflectiveOperationException the reflective operation exception
     */
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
    public static <T> Object getterField(T tclass, String nameField) throws ReflectiveOperationException {

        try {
            PropertyDescriptor pd = new PropertyDescriptor(nameField, tclass.getClass());
            Info.print(Reflective.class,"nameField {0}",pd.getReadMethod().getDefaultValue());
            return pd.getReadMethod().invoke(tclass);
        } catch (IllegalArgumentException | InvocationTargetException | IntrospectionException |
                 IllegalAccessException e) {
            log.error("Erreur setter", e);
            throw new ReflectiveOperationException("Cannot get :" + nameField);
        }
    }
}
