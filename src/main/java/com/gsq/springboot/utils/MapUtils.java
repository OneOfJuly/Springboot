package com.gsq.springboot.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot_project
 * @Date: 2019/4/12 16:47
 * @Author: Mr.GUO
 * @Description:
 */
public class MapUtils {

    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
        ConvertUtils.register(new Converter(){
        @SuppressWarnings("rawtypes")
        @Override
        public Object convert(Class arg0, Object arg1) {
            System.out.println("注册字符串转换为date类型转换器");
            if(arg1 == null) {
                return null;
            }
            if(!(arg1 instanceof String)) {
                throw new ConversionException("只支持字符串转换 !");
            }
            String str = (String)arg1;
            if(str.trim().equals("")){
                return null;
            }
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            try{
                return sd.parse(str);
            }catch(ParseException e) {
                throw new RuntimeException(e);
            }
             }
            }, java.util.Date.class);
            T bean = null;
            try {
                bean = beanClass.newInstance();
                BeanUtils.populate(bean, map);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return bean;

    }
    /** * 使用Introspector，对象转换为map集合 * * @param beanObj javabean对象 * @return map集合 */
    public static Map<String, Object> beanToMap(Object beanObj) {
        if (null == beanObj) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(beanObj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(beanObj) : null;
                map.put(key, value);
            }
            return map;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

}
