package com.cnc.common.web.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 传值有效性验证
 * 作者： cnc
 * 创建时间：2017-06-10.
 * 版本：1.0
 */
public class SpringValidUtil {
    public static Map<String, String> getErrors(BindingResult result){
        Map<String, String> map = new HashMap<>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }

    public static String getErrors2Str(BindingResult result) {
        StringBuffer errStr = new StringBuffer();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list){
            errStr.append(error.getDefaultMessage()+",");
        }

        if (errStr.length() > 0){
            return errStr.substring(0,errStr.length()-1);
        }
        return errStr.toString();
    }
}
