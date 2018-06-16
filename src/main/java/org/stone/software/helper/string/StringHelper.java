package org.stone.software.helper.string;

/**
 * <×Ö·û´®¹¤¾ßÀà>
 * 
 * @author StoneSoftware
 * 
 */
public class StringHelper
{
    
    /**
     * <ÅÐ¶Ï×Ö·û´®Îª¿Õ>
     * 
     * @param str ´ýÅÐ¶Ï×Ö·û´®
     * @return boolean true×Ö·û´®Îª¿Õ,false×Ö·û´®²»Îª¿Õ
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
     * <ÅÐ¶Ï×Ö·û´®²»Îª¿Õ>
     * 
     * @param str ´ýÅÐ¶Ï×Ö·û´®
     * @return boolean true×Ö·û´®²»Îª¿Õ,false×Ö·û´®Îª¿Õ
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }
    
    /**
     * <ÅÐ¶ÏÁ½¸ö×Ö·û´®ÊÇ·ñÏàÍ¬>
     * 
     * @param str1 ×Ö·û´®1
     * @param str2 ×Ö·û´®2
     * @return boolean trueÁ½¸ö×Ö·û´®(str1,str2)ÏàÍ¬;falseÁ½¸ö×Ö·û´®(str1,str2)²»Í¬
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
     * <ºöÂÔ´óÐ¡Ð´Æ¥Åä×Ö·û´®>
     * 
     * @param str1 ×Ö·û´®1
     * @param str2 ×Ö·û´®2
     * @return boolean trueÁ½¸ö×Ö·û´®(str1,str2)ÏàÍ¬;falseÁ½¸ö×Ö·û´®(str1,str2)²»Í¬
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
