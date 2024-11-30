package com.wordbook.common.util;

import org.springframework.beans.BeanUtils;

public class ConvertBeanUtils {

    // 转换object种类，比如：将 UserDAO 类型转换为 UserDTO 类型
    public static <T> T convert(Object source, Class<T> target) {
        if(source == null){
            return null;
        }
        T targetObject = null;
        try {
            targetObject=target.newInstance();
            // 不为空则转换
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetObject;
    }
}
