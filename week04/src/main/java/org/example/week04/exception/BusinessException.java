package org.example.week04.exception;

import lombok.Getter;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/3/27 16:42 // 创建日期 + 时间
 * @description 业务异常类
 */
@Getter
public class BusinessException extends RuntimeException {
    private final int code;
    
    public BusinessException(String msg) {
        super(msg);
        this.code = 500;
    }
    
    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
