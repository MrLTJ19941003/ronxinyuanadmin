package com.ronxinyuan.common;

/**
 * Created by 13045 on 2018/3/26.
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        boolean b = false;
        if(str==null || str=="" || str == " " || str.equals("") || str.equals(" ")){
            b = true;
        }
        return b;
    }

    public static int MyparseInt(String string)throws Exception{
        try{
            return Integer.parseInt(string);
        }catch (NumberFormatException e){
            throw e;
        }catch (NullPointerException e){
            throw e;
        }

    }
}
