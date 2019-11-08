package com.example.common.pojo.constant.enumtype;

/**
 * @ClassName YNFlagStatusEnum
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/11 8:55
 * @Version 1.0
 **/
public enum  YNFlagStatusEnum {
    VALID(1,"有效"),
    FAIL(0,"无效");

    private YNFlagStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String desc;
    private Integer code;

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }

    public String toString(){
        return code+","+desc;
    }

    /**
     * 通过key 查找描述 方法
     *
     * @param key
     *            .
     * @return string 返回对应名称.
     * @author ：jrhelin
     */
    public static String getValueByKey(String key) {
        for (YNFlagStatusEnum pm : YNFlagStatusEnum.values()) {
            if (pm.getCode().equals(key)) {
                return pm.getDesc();
            }
        }
        return null;
    }
}
