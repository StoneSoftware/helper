package org.stone.software.helper.collection;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.stone.software.helper.reflection.ReflectionUtil;

public class CollectionHelper
{
    
    /**
     * <�жϼ����Ƿ�Ϊ�ն����Ԫ�ظ���Ϊ��>
     * 
     * @param collection ���ж�Ԫ��
     * @return boolean trueΪ��Ԫ��,false��Ϊ��Ԫ��
     */
    public static boolean isEmpty(Collection<? extends Object> collection)
    {
        if (collection == null)
        {
            return true;
        }
        return collection.isEmpty();
    }
    
    /**
     * <�жϼ����Ƿ�Ϊ�ǿն����Ԫ�ظ���������>
     * 
     * @param collection ���ж�Ԫ��
     * @return falseΪ��Ԫ��,true��Ϊ��Ԫ��
     */
    public static boolean isNotEmpty(Collection<? extends Object> collection)
    {
        return !isEmpty(collection);
    }
    
    /**
     * <��Collection�е�Ԫ�ذ��ո������Ե�ֵ��װ��Map>
     * 
     * @param colleciton ����Ԫ��
     * @param key �����ֶ�
     * @return Map
     */
    public static <T> Map<String, T> toMap(Collection<T> colleciton,
        String field)
    {
        Map<String, T> map = new HashMap<String, T>();
        if (isNotEmpty(colleciton))
        {
            colleciton.stream().forEach(ele -> {
                Optional<Method> method =
                    ReflectionUtil.obtainGetMethod(ele, field);
                method.ifPresent(e2 -> {
                    Object obj = null;
                    Method m = method.get();
                    try
                    {
                        obj = m.invoke(ele);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    if (obj instanceof String || obj instanceof Integer
                        || obj instanceof Float || obj instanceof Double)
                    {
                        map.put(obj.toString(), ele);
                    }
                });
            });
        }
        return map;
    }
}
