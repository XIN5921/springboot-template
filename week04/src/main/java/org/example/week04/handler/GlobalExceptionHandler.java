package org.example.week04.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.week04.common.Result;
import org.example.week04.exception.BusinessException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.StringJoiner;

/**
 * @author ZhangXin
 * @date 2026/3/27 16:44
 * @description 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验异常
     * @param e 参数校验异常
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidationException(MethodArgumentNotValidException e) {
        StringJoiner sj = new StringJoiner(", ");
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            sj.add(fieldError.getDefaultMessage());
        }
        log.warn("参数校验失败：{}", sj);
        return Result.error(400, sj.toString());
    }

    /**
     * 处理业务异常
     * @param e 业务异常
     * @return 错误信息
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        log.warn("业务异常：code={}, message={}", e.getCode(), e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理文件上传大小超限异常
     * @param e 文件上传大小超限异常
     * @return 错误信息
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<?> handleMaxSizeException(MaxUploadSizeExceededException e) {
        log.warn("文件大小超出限制：{}", e.getMessage());
        return Result.error(413, "文件大小超出限制（最大 10MB）");
    }

    /**
     * 处理运行时异常
     * @param e 运行时异常
     * @return 错误信息
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<?> handleRuntimeException(RuntimeException e) {
        log.error("运行时异常：{}", e.getMessage(), e);
        return Result.error(500, e.getMessage() != null ? e.getMessage() : "运行时异常");
    }

    /**
     * 处理其他异常
     * @param e 异常
     * @return 错误信息
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("系统异常：{}", e.getMessage(), e);
        return Result.error(500, "服务器异常：" + e.getMessage());
    }
}
