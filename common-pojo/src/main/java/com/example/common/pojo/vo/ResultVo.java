package com.example.common.pojo.vo;

import lombok.Data;

/**
 * @ClassName ResultVo
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/10 17:33
 * @Version 1.0
 **/
@Data
public class ResultVo<T> implements java.io.Serializable {
    private static final long serialVersionUID = 752386055477259305L;

    public static final String SUCCESS = "SUCCESS";
    public static final Integer SUCCESS_CODE = 20000;

    private boolean result = true;
    private Integer code = SUCCESS_CODE;
    private String message;
    private T data;

    public ResultVo() {
    }

    public ResultVo(boolean result, T data) {
        this.result = result;
        this.data = data;
    }

    public ResultVo(boolean result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResultVo(boolean result, String message, T data, Integer code) {
        this.result = result;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public static <T> ResultVo<T> getSuccess(String message, T data, Integer code) {
        return new ResultVo<T>(true, message, data, code);
    }

    public static <T> ResultVo<T> getSuccess(String message, T data) {
        return getSuccess(message, data, SUCCESS_CODE);
    }

    public static <T> ResultVo<T> getSuccess(T data) {
        return getSuccess(SUCCESS, data);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
