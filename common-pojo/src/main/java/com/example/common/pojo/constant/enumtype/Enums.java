package com.example.common.pojo.constant.enumtype;

public interface Enums {
    Integer getCode();
    String getDescribe();

    public static <T extends Enums> T getEnum(Integer code , Class<T> enums){
        //循环遍历你的枚举类中的所有code值，相等则返回给调用者
        for (T each:enums.getEnumConstants()) {
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
