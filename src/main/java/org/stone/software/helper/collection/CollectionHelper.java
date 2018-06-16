package org.stone.software.helper.collection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.stone.software.helper.reflection.ReflectionUtil;

public class CollectionHelper
{
    
    /**
     * <判断集合是否为空对象和元素个数为零>
     * 
     * @param collection 待判断元素
     * @return boolean true为空元素,false不为空元素
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
     * <判断集合是否为非空对象和元素个数大于零>
     * 
     * @param collection 待判断元素
     * @return false为空元素,true不为空元素
     */
    public static boolean isNotEmpty(Collection<? extends Object> collection)
    {
        return !isEmpty(collection);
    }
    
    /**
     * <将Collection中的元素按照给定属性的值封装成Map>
     * 
     * @param colleciton 集合元素
     * @param key 对象字段
     * @return Map
     */
    public static <T> Map<String, T> toMap(Collection<T> colleciton, String key)
    {
        Class<?> clazz = null;
        Method method = null;
        Map<String, T> map = new HashMap<String, T>();
        try
        {
            if (isNotEmpty(colleciton))
            {
                for (T t : colleciton)
                {
                    if (clazz == null)
                    {
                        clazz = t.getClass();
                        method = ReflectionUtil.getMethod(t, key);
                    }
                    Object obj = method.invoke(t);
                    if (obj instanceof String || obj instanceof Integer
                        || obj instanceof Float || obj instanceof Double)
                    {
                        map.put(obj.toString(), t);
                    }
                }
            }
        }
        catch (IllegalAccessException | IllegalArgumentException
            | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return map;
    }
}
