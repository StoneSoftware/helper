package org.stone.software.helper.string;

/**
 * <�ַ���������>
 * 
 * @author StoneSoftware
 * 
 */
public class StringHelper
{
    
    /**
     * <�ж��ַ���Ϊ��>
     * 
     * @param str ���ж��ַ���
     * @return boolean true�ַ���Ϊ��,false�ַ�����Ϊ��
     */
    public static boolean isEmpty(String str)
    {
        
        if (str == null)
        {
            return true;
        }
        str = str.replaceAll("\\s+", "");
        if (str.equals(""))
        {
            return true;
        }
        return false;
    }
    
    /**
     * <�ж��ַ�����Ϊ��>
     * 
     * @param str ���ж��ַ���
     * @return boolean true�ַ�����Ϊ��,false�ַ���Ϊ��
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }
    
    /**
     * <�ж������ַ����Ƿ���ͬ>
     * 
     * @param str1 �ַ���1
     * @param str2 �ַ���2
     * @return boolean true�����ַ���(str1,str2)��ͬ;false�����ַ���(str1,str2)��ͬ
     */
    public static boolean equal(String str1, String str2)
    {
        if (str1 == str2)
        {
            return true;
        }
        if (str1 != null && str2 != null && str1.equals(str2))
        {
            return true;
        }
        return false;
        
    }
    
    /**
     * <���Դ�Сдƥ���ַ���>
     * 
     * @param str1 �ַ���1
     * @param str2 �ַ���2
     * @return boolean true�����ַ���(str1,str2)��ͬ;false�����ַ���(str1,str2)��ͬ
     */
    public static boolean equalsIgnoreCase(String str1, String str2)
    {
        if (str1 == str2)
        {
            return true;
        }
        if (str1 != null && str2 != null && str1.equalsIgnoreCase(str2))
        {
            return true;
        }
        return false;
        
    }
    
}
