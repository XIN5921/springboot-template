package org.example.week05.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.week05.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("系统异常：", e);
        e.printStackTrace();
        return Result.error("系统异常：" + e.getMessage());
    }
}