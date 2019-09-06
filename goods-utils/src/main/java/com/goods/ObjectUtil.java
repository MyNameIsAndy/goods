package com.goods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by apple on 2017/9/11.
 */
@Component
@Slf4j
public class ObjectUtil {

    public void getObjctFromMap(Object obj, Map map) {
        Class cls = obj.getClass();
        Field fieldlist[] = cls.getDeclaredFields();
        try {
            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                String FieldType = fld.getType().toString();
                String FieldName = fld.getName().toUpperCase();
                fld.setAccessible(true);//设置可以访问私有变量
                if (FieldType.equalsIgnoreCase("long")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.setLong(obj, 0L);
                    else
                        fld.setLong(obj, Long.valueOf(
                                map.get(FieldName).toString().trim()).longValue());
                } else if (FieldType.equalsIgnoreCase("short")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.setShort(obj, (short) 0);
                    else
                        fld.setShort(obj, Short.valueOf(
                                map.get(FieldName).toString().trim()).shortValue());
                } else if (FieldType.equalsIgnoreCase("int")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.setInt(obj, 0);
                    else
                        fld.setInt(obj, Integer.valueOf(
                                map.get(FieldName).toString().trim()).intValue());
                } else if (FieldType.equalsIgnoreCase("byte")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.setByte(obj, (byte) 0);
                    else
                        fld.setByte(obj, Byte.valueOf(
                                map.get(FieldName).toString().trim()).byteValue());
                } else if (FieldType.equalsIgnoreCase("float")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.setFloat(obj, 0.0F);
                    else
                        fld.setFloat(obj, Float.valueOf(
                                map.get(FieldName).toString().trim()).floatValue());
                } else if (FieldType.equalsIgnoreCase("double")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.setDouble(obj, 0.0D);
                    else
                        fld
                                .setDouble(obj, Double.valueOf(
                                        map.get(FieldName).toString().trim())
                                        .doubleValue());
                } else if (FieldType.equalsIgnoreCase("boolean")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.setBoolean(obj, false);
                    else
                        fld.setBoolean(obj, Boolean.valueOf(
                                map.get(FieldName).toString().trim())
                                .booleanValue());
                } else if (FieldType.equalsIgnoreCase("class java.util.Date")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, DateTimeUtil.parseDate("1899-12-31", "yyyy-MM-dd"));
                    else
                        fld.set(obj, DateTimeUtil.parseDate(map.get(FieldName).toString()
                                .trim(), "yyyy-MM-dd"));
                } else if (FieldType.equalsIgnoreCase("class java.lang.String")) {
                    //
                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, "");
                    else
                        fld.set(obj, map.get(FieldName).toString().trim());
                } else if (FieldType.equalsIgnoreCase("class java.sql.Timestamp")) {
                    //
                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, Timestamp.valueOf("1899-12-31 12:00:00.123"));
                    else
                        fld.set(obj, (Timestamp) map.get(FieldName));
                } else if (FieldType.equalsIgnoreCase("class java.lang.Integer")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, Integer.valueOf(0));
                    else
                        fld.set(obj, Integer.valueOf(map.get(FieldName)
                                .toString().trim()));
                } else if (FieldType.equalsIgnoreCase("class java.lang.Double")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, Double.valueOf(0.0D));
                    else
                        fld.set(obj, Double.valueOf(map.get(FieldName).toString()
                                .trim()));
                } else if (FieldType.equalsIgnoreCase("class java.lang.Float")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, Float.valueOf(0.0F));
                    else
                        fld.set(obj, Float.valueOf(map.get(FieldName).toString()
                                .trim()));
                } else if (FieldType.equalsIgnoreCase("class java.lang.Short")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, Short.valueOf((short) 0));
                    else
                        fld.set(obj, Short.valueOf(map.get(FieldName).toString()
                                .trim()));
                } else if (FieldType.equalsIgnoreCase("class java.lang.Long")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, Long.valueOf(0L));
                    else
                        fld.set(obj, Long.valueOf(map.get(FieldName).toString()
                                .trim()));
                } else if (FieldType.equalsIgnoreCase("class java.math.BigDecimal")) {

                    if (map.get(FieldName) == null
                            || map.get(FieldName).toString().trim()
                            .equalsIgnoreCase("null"))
                        fld.set(obj, new BigDecimal(0));
                    else
                        fld.set(obj, new BigDecimal(map.get(FieldName).toString()
                                .trim()));
                } else {
                    System.out.println((new StringBuilder("不支持的数据类型:[")).append(
                            FieldType).append("]").toString());
                    fld.set(obj, null);
                }
            }
        } catch (Exception e) {
            System.out.println("-----------------从map转对象时出现异常---------------");
//            e.printStackTrace();
        }
    }

    /**
     * 根据实体字段名得到相应的value值
     *
     * @param fieldName
     * @param obj
     * @return
     */
    public Object getFieldValueByName(String fieldName, Object obj) {
        Method method;
        Object rtnObj = null;
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
//			char[] array = fieldName.toCharArray();
//			array[0] -= 32;
//			fieldName = String.valueOf(array);
            String fieldType = field.getType().toString();
            fieldName = StringUtil.upperFirst(fieldName);
            method = obj.getClass().getMethod("get" + fieldName, null);
            rtnObj = method.invoke(obj, null);
            if (fieldType.equalsIgnoreCase("class java.sql.Timestamp")) {
                rtnObj = DateTimeUtil.toDateTimeString((Timestamp) rtnObj);
            } else {
                rtnObj = String.valueOf(rtnObj);
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rtnObj;
    }

    /**
     * 根据字段名设置相应的值
     *
     * @param fieldName
     * @param value
     * @param obj
     */
    public void setFieldValueByName(String fieldName, String value, Object obj) {
        Field field;
        try {
            field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);//设置可以访问私有变量
            field.set(obj, value);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 利用反射实现对象之间属性复制
     *
     * @param from
     * @param to
     */
    public void copyProperties(Object from, Object to) throws Exception {
        copyPropertiesExclude(from, to, null);
    }

    /**
     * 复制对象属性
     *
     * @param from
     * @param to
     * @param excludsArray 排除属性列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void copyPropertiesExclude(Object from, Object to, String[] excludsArray) throws Exception {
        List<String> excludesList = new ArrayList<>();
        if (excludsArray != null && excludsArray.length > 0) {
            for (String str : excludsArray)
                excludesList.add(str.toLowerCase());//构造列表对象
        }
        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method fromMethod = null, toMethod = null;
        String fromMethodName = null, toMethodName = null;
        for (int i = 0; i < fromMethods.length; i++) {
            fromMethod = fromMethods[i];
            fromMethodName = fromMethod.getName();
            if (!fromMethodName.contains("get"))
                continue;
            //排除列表检测
            if (excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {
                continue;
            }
            toMethodName = "set" + fromMethodName.substring(3);
            toMethod = findMethodByName(toMethods, toMethodName);
            if (toMethod == null)
                continue;
            Object value = fromMethod.invoke(from, new Object[0]);
            if (value == null)
                continue;
            //集合类判空处理
            if (value instanceof Collection) {
                Collection newValue = (Collection) value;
                if (newValue.size() <= 0)
                    continue;
            }
            toMethod.invoke(to, new Object[]{value});
        }
    }

    /**
     * 对象属性值复制，仅复制指定名称的属性值
     *
     * @param from
     * @param to
     * @param includsArray
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void copyPropertiesInclude(Object from, Object to, String[] includsArray) {
        List<String> includesList = null;
        if (includsArray != null && includsArray.length > 0) {
            includesList = Arrays.asList(includsArray);    //构造列表对象
        } else {
            return;
        }
        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method fromMethod = null, toMethod = null;
        String fromMethodName = null, toMethodName = null;
        for (int i = 0; i < fromMethods.length; i++) {
            fromMethod = fromMethods[i];
            fromMethodName = fromMethod.getName();
            if (!fromMethodName.contains("get"))
                continue;
            //排除列表检测
            String str = fromMethodName.substring(3);
            if (!includesList.contains(str.substring(0, 1).toLowerCase() + str.substring(1))) {
                continue;
            }
            toMethodName = "set" + fromMethodName.substring(3);
            toMethod = findMethodByName(toMethods, toMethodName);
            if (toMethod == null)
                continue;
            Object value = null;
            try {
                value = fromMethod.invoke(from, new Object[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (value == null)
                continue;
            //集合类判空处理
            if (value instanceof Collection) {
                Collection newValue = (Collection) value;
                if (newValue.size() <= 0)
                    continue;
            }
            try {
                toMethod.invoke(to, new Object[]{value});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 从方法数组中获取指定名称的方法
     *
     * @param methods
     * @param name
     * @return
     */
    public Method findMethodByName(Method[] methods, String name) {
        for (int j = 0; j < methods.length; j++) {
            if (methods[j].getName().equals(name))
                return methods[j];
        }
        return null;
    }

    public Map<String, Object> objectToMap(Object obj) {
        if (obj == null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = null;
            try {
                value = getter != null ? getter.invoke(obj) : null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        }
        return map;
    }


    /**
     * 判断对象为空
     * @param obj 对象名
     * @return 是否为空
     */
    public boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return "".equals(obj) ;
        }
        return false;
    }


}
