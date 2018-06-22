package com.jyou.utils;

import com.alibaba.fastjson.JSON;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.Serializable;

/**
 * @describe:  返回值ResultMap
 * @author: RenXin
 * @version: V1.0
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ResultMap implements Serializable {


    /**
     * 返回响应码
     */
    private Integer code;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 返回时的响应信息
     */
    private String message;

    private ResultMap() {
    }

    public ResultMap(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

    private ResultMap(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    private ResultMap(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultMap(Integer code) {
        this.code = code;
    }

    private ResultMap(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static ResultMap getResultMap() {
        return new ResultMap();
    }

    public static ResultMap getResultMap(Integer code, Object data) {
        return new ResultMap(code, data);
    }

    public static ResultMap getResultMap(Integer code) {
        return new ResultMap(code);
    }

    public static ResultMap getResultMap(Integer code, String message, Object data) {
        return new ResultMap(code, data, message);
    }

    public static ResultMap getResultMap(Integer code, String message) {
        return new ResultMap(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
