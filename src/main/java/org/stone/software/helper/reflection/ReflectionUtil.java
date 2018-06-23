package org.stone.software.helper.reflection;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Optional;

import org.stone.software.helper.string.StringHelper;

public class ReflectionUtil
{
    
    public static <T> Optional<Method> obtainGetMethod(T t, String fieldName)
    {
        if (t == null)
        {
            return Optional.empty();
        }
        return obtainGetMethod(t.getClass(), fieldName);
    }
    
    public static <T> Optional<Method> obtainGetMethod(Class<?> clazz,
        String fieldName)
    {
        Method method = null;
        String getMethodName = null;
        if (fieldName.startsWith("is"))
        {
            getMethodName = fieldName;
        }
        else
        {
            String firstStr = fieldName.substring(0, 1);
            getMethodName =
                "get" + firstStr.toUpperCase(Locale.CHINA)
                    + fieldName.replaceFirst(firstStr, "");
            if (clazz != null && StringHelper.isNotEmpty(getMethodName))
            {
                try
                {
                    method = clazz.getMethod(getMethodName);
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        return Optional.ofNullable(method);
    }
}
