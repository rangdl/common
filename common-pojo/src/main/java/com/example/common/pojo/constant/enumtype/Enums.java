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
    public enum  ResultEnum implements Enums {
        SUCCESS(20000,"成功!"),
        SUCCESS_CHECK(20001,"验证成功!"),
        SUCCESS_REQUEST(20002,"请求成功!"),
        SUCCESS_SAVE(20003,"保存成功!"),
        SUCCESS_MODIFY(20004,"修改成功!"),
        SUCCESS_REMOVE(20005,"删除成功!"),

        ERROR_PASSWORD(40001,"密码错误!"),
        ERROR_NOT_EXIST_USER(40002,"用户不存在!"),
        ERROR_NOT_EXIST_ACCOUNT(40003,"账户不存在!"),
        ERROR_LOCKING(40004,"已被锁定!"),
        ERROR_EXIST_USER(40005,"账号已经存在!"),
        ERROR_NOT_ALLOW_MODIFY(40006,"账号不能修改!"),
        ERROR_PASSWORD_PRIMARY(40007,"原密码错误!"),

        SERVER_ERROR(50000,"服务端错误!"),

        PARAMETERS_MISSING(50002,"参数不完整!"),

        TOKEN_CHECK_ILLEFALITY_ERROR(50008,"非法token!"),

        TOKEN_CHECK_OTHER_LOGIN(50012,"其他客户端登录!"),

        TOKEN_CHECK_STALE_DATED(50014,"token 过期!");


        private ResultEnum(Integer code, String describe) {
            this.code = code;
            this.describe = describe;
        }

        private Integer code;
        private String describe;

        @Override
        public String getDescribe() {
            return describe;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        public String toString(){
            return code+","+describe;
        }

        /**
         * 通过key 查找描述 方法
         *
         * @param key
         *            .
         * @return string 返回对应名称.
         * @author ：jrhelin
         */
        public static String getDescribeByKey(String key) {
            for (ResultEnum pm : ResultEnum.values()) {
                if (pm.getCode().equals(key)) {
                    return pm.getDescribe();
                }
            }
            return null;
        }
    }
}
