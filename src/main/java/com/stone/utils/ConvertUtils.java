package com.stone.utils;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author chen
 * @create 2020-02-14 08:56
 **/

public class ConvertUtils {


    /**
     * 模糊查询，将需要拼接的字段传参
     * @param object
     * @param fieldNames
     * @return
     */
    public static Object convertLike(Object object, String... fieldNames) {
        Class clazz = object.getClass();

        try {
            for (String fieldName : fieldNames) {
                if (StringUtils.isEmpty(fieldName)) {
                    continue;
                }

                PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
                Method readMethod = descriptor.getReadMethod();
                Object value = readMethod.invoke(object);
                if (StringUtils.isEmpty(value.toString())) {
                    continue;
                }

                Method writeMethod = descriptor.getWriteMethod();
                writeMethod.setAccessible(true);
                writeMethod.invoke(object, "%" + value.toString() + "%");
            }
        } catch (Exception e) {

        }

        return object;
    }

}
