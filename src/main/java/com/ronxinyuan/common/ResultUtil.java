package com.ronxinyuan.common;


import com.ronxinyuan.bean.Result;

/**
 * Created by 13045 on 2018/2/23.
 */
public class ResultUtil {
    public static Result success(int result){
        if(result > 0){
            return new Result(200,"success");
        }
        return new Result(500,"faild");
    }
}
