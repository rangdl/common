package com.example.common.pojo.vo;

import com.example.common.pojo.constant.enumtype.Enums;
import com.example.common.pojo.constant.enumtype.ResultEnum;
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

    private boolean result = true;
    private Integer code = ResultEnum._200.getCode();
    private String message = ResultEnum._200.getDescribe();
    private T data;

    public ResultVo() {
    }

    public ResultVo(Enums enums, T data) {
        if (enums.getCode() % ResultEnum._200.getCode() < 100)
            this.result = true;
        else this.result = false;
        this.code = enums.getCode();
        this.message = enums.getDescribe();
        this.data = data;
    }

    public static <T> ResultVo<T> getSuccess() {
        return new ResultVo<T>();
    }
    public static <T> ResultVo<T> getSuccess(T data) {
        return new ResultVo<T>(ResultEnum._200,data);
    }
    public static <T> ResultVo<T> getError() {
        return getError(null);
    }
    public static <T> ResultVo<T> getError(T data) {
        return new ResultVo<T>(ResultEnum._500,data);
    }

    public static <T> ResultVo<T> getResultVo(Enums enums) {
        return new ResultVo<T>(enums,null);
    }
    public static <T> ResultVo<T> getResultVo(Enums enums,T data) {
        return new ResultVo<T>(enums,data);
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
