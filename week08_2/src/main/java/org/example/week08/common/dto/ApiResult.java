package org.example.week08.common.dto;

import lombok.Data;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/24 16:30 // 创建日期+时间
 * @description 类描述信息
 */
@ Data
public class ApiResult<T> {
    private int code;
    private String message;
    private T data;

    private ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应
     * @param data 响应数据
     * @param <T> 数据类型
     * @return ApiResult实例
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(200, "success", data);
    }

    /**
     * 错误响应
     * @param code 错误码
     * @param message 错误消息
     * @param <T> 数据类型
     * @return ApiResult实例
     */
    public static <T> ApiResult<T> error(int code, String message) {
        return new ApiResult<>(code, message, null);
    }
}