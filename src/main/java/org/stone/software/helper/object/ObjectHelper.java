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
     * <��������,��ȡ����������Ĺ����ֶ�,�Ƚ��������������ֶ�����ֵ�Ƿ�һ�¡������һ�����¼��org.json.JSONObject��,
     * Ȼ�󷵻�org.json.JSONObject >
     * 
     * @param k ʵ�����
     * @param t ʵ�����
     * @param commonFieldList �����ֶ�
     * @return ���컯����
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
     * <ɸѡ��������Ĺ����ֶ�>
     * 
     * @param cla ����
     * @param clb ����
     * @param annotation ����ע��
     * @param isAll �Ƿ�ɸѡ�������ֶ�
     * @return ����ɸѡ��Ĺ����ֶ�
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
     * <��ȡ����������ֶ���>
     * 
     * @param cla ����
     * @param isAll �Ƿ��ȡ������������ֶ���
     * @return ��ȡ��Ķ����ֶ���
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
     * <��ȡָ���������������java.lang.reflect.Field>
     * 
     * @param cla ָ������
     * @param isAll �Ƿ��ȡ���������������
     * @return ��ȡ�����������
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
