package org.stone.software.helper.reflection;

import java.lang.reflect.Method;
import java.util.Locale;

public class ReflectionUtil
{
    
    public static Method getMethod(Class<?> clazz, String fieldName)
    {
        String getMethodName = null;
        Method method = null;
        try
        {
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
                method = clazz.getMethod(getMethodName);
            }
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            e.printStackTrace();
        }
        return method;
    }
    
    public static void setMethod()
    {
        
    }
    
}
