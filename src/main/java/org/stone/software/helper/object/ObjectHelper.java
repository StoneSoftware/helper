package org.stone.software.helper.object;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import org.json.JSONObject;

public class ObjectHelper
{
    /**
     * <两个对象,获取这两个对象的公共字段,比较这两个对象工作字段属性值是否一致。如果不一致则记录到org.json.JSONObject中,
     * 然后返回org.json.JSONObject >
     * 
     * @param k 实体对象
     * @param t 实体对象
     * @param commonFieldList 公共字段
     * @return 差异化数据
     */
    public static <K, T> JSONObject getSameFieldsDifferentValue(K k, T t,
        List<String> commonFieldList)
    {
        JSONObject jsonObj = new JSONObject();
        Objects.requireNonNull(k);
        Objects.requireNonNull(t);
        Objects.requireNonNull(commonFieldList);
        try
        {
            Class<?> cla = k.getClass();
            Class<?> clb = t.getClass();
            String getName = "";
            for (String field : commonFieldList)
            {
                String firstStr = field.substring(0, 1);
                if (field.startsWith("is"))
                {
                    getName = field;
                }
                else
                {
                    getName =
                        "get"
                            + field.replaceFirst(firstStr,
                                firstStr.toUpperCase(Locale.CHINA));
                }
                Method method1 = cla.getMethod(getName);
                Method method2 = clb.getMethod(getName);
                Object obj1 = method1.invoke(k);
                Object obj2 = method2.invoke(t);
                String value1 = obj1 == null ? "" : obj1.toString().trim();
                String value2 = obj2 == null ? "" : obj2.toString().trim();
                if (!value1.equals(value2))
                {
                    jsonObj.put(field, value1);
                }
            }
        }
        catch (IllegalAccessException | IllegalArgumentException
            | InvocationTargetException | NoSuchMethodException
            | SecurityException e)
        {
            e.printStackTrace();
        }
        return jsonObj;
    }
    
    /**
     * <筛选两个对象的公共字段>
     * 
     * @param cla 对象
     * @param clb 对象
     * @param annotation 忽略注解
     * @param isAll 是否筛选父对象字段
     * @return 返回筛选后的公共字段
     */
    public static <K, T> List<String> getSameField(Class<K> cla, Class<T> clb,
        Class<? extends Annotation> annotation, boolean isAll)
    {
        Objects.requireNonNull(cla);
        Objects.requireNonNull(clb);
        List<String> commonFieldList = new ArrayList<String>(50);
        Set<String> fieldNameSet = new HashSet<String>();
        List<Field> field1List = getObjectField(cla, isAll);
        for (Field f1 : field1List)
        {
            if (f1.isAnnotationPresent(annotation))
            {
                fieldNameSet.add(f1.getName());
            }
        }
        List<Field> field2List = getObjectField(clb, isAll);
        for (Field f2 : field2List)
        {
            if (!f2.isAnnotationPresent(annotation))
            {
                if (fieldNameSet.contains(f2.getName()))
                {
                    commonFieldList.add(f2.getName());
                }
            }
        }
        return commonFieldList;
    }
    
    /**
     * <获取对象的所有字段名>
     * 
     * @param cla 对象
     * @param isAll 是否获取父对象的所有字段名
     * @return 获取后的对象字段名
     */
    public static <T> List<String> getObjectFieldNames(Class<T> cla,
        boolean isAll)
    {
        List<Field> allFieldList = new ArrayList<Field>();
        List<String> allFieldNameList = new ArrayList<String>();
        Field[] fields = cla.getDeclaredFields();
        if (fields != null && fields.length > 0)
        {
            allFieldList.addAll(Arrays.asList(fields));
        }
        if (isAll)
        {
            get(cla, allFieldList);
        }
        for (Field field : allFieldList)
        {
            allFieldNameList.add(field.getName());
        }
        return allFieldNameList;
    }
    
    /**
     * <获取指定对象的所有属性java.lang.reflect.Field>
     * 
     * @param cla 指定对象
     * @param isAll 是否获取父对象的所有属性
     * @return 获取后的所有属性
     */
    public static <T> List<Field> getObjectField(Class<T> cla, boolean isAll)
    {
        List<Field> allFieldList = new ArrayList<Field>();
        Field[] fields = cla.getDeclaredFields();
        if (fields != null && fields.length > 0)
        {
            allFieldList.addAll(Arrays.asList(fields));
        }
        if (isAll)
        {
            get(cla, allFieldList);
        }
        return allFieldList;
    }
    
    private static <T> void get(Class<T> cla, List<Field> fieldList)
    {
        Class<? super T> superClass = cla.getSuperclass();
        if (superClass == Object.class)
        {
            return;
        }
        else
        {
            Field[] fields = superClass.getDeclaredFields();
            if (fields != null && fields.length > 0)
            {
                fieldList.addAll(Arrays.asList(fields));
            }
            get(superClass, fieldList);
        }
    }
}
