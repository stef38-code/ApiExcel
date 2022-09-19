package org.api.excel.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ClassTools {
    private static Logger log = LoggerFactory.getLogger(ClassTools.class);

    public <T> T getNewInstance(Class<T> tclass) throws IllegalAccessException {
        try {
            return tclass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Erreur creation nouvelle instance", e);
            throw new IllegalAccessException("Cannot create instance :" + tclass.getSimpleName());
        }
    }

    public <T> void setterField(T tclass, String nameField, Object value) throws IllegalAccessException {
        PropertyDescriptor pd = null;
        try {
            pd = new PropertyDescriptor(nameField, tclass.getClass());
            pd.getWriteMethod().invoke(tclass, value);
        } catch (IllegalArgumentException | InvocationTargetException | IntrospectionException | IllegalAccessException e) {
            log.error("Erreur setter", e);
            throw new IllegalAccessException("Cannot set :" +  nameField);
        }
    }

    private String getTypeValue(PropertyDescriptor pd) {
        if (Objects.isNull(pd)) {
            return "!! UNDEFINE !!";
        }
        return pd.getReadMethod().getReturnType().getSimpleName();
    }
}
