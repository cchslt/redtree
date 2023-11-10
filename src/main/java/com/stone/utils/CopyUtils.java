package com.stone.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen
 * @create 2020-02-14 09:19
 **/

public class CopyUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CopyUtils.class);


    /**
     * 复制List集合
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List source, Class targetClass) {
        if (CollectionUtils.isEmpty(source) || targetClass == null) {
            return null;
        }

        List<T> targetList = new ArrayList<T>(source.size());

        T target = null;
        for (int i = 0, j = source.size(); i < j; i++) {
            try{
                target = (T)targetClass.newInstance();
                BeanUtils.copyProperties(source.get(i), target);
            } catch (Exception e) {
                LOGGER.error("【复制集合error class: {}, cause by: {}", targetClass.toString(), e.getMessage());
                return null;
            }

            targetList.add(target);
        }

        return targetList;
    }


    /**
     * bean复制
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T extends Object> T copyBean(Object source, Class targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }

        T target = null;
        try {
            target = (T)targetClass.newInstance();
            BeanUtils.copyProperties(source, target);

        } catch (Exception e) {
            LOGGER.error("[复制bean error] cause by: {}", e.getMessage());
        }

        return target;
    }


    /**
     * 属性值复制
     * @param source
     * @param target
     */
    public static void copyBean(Object source, Object target) {
        if (source != null && target != null) {
            try {
                BeanUtils.copyProperties(source, target);

            } catch (Exception e) {
                LOGGER.error("[复制bean error] cause by: {}", e.getMessage());
            }
        }
    }


    /**
     * 获取value为null的Fields
     * @param source
     * @return
     */
    public static String[] nullPropertyNames(Object source) {
        if (source == null) {
            return null;
        }

        Set<String> emptyNames = new HashSet<String>();

        Class clazz = source.getClass();
        Field[] declaredFieldList = clazz.getDeclaredFields();
        for (Field field : declaredFieldList) {
            String fieldName = field.getName();

            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
                Method readMethod = descriptor.getReadMethod();

                Object value = readMethod.invoke(source);
                if (value == null) {
                    emptyNames.add(fieldName);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);

    }

}
