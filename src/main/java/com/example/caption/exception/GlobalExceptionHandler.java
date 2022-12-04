package com.example.caption.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> defaultExceptionHandler(Exception ex) {
        log.error("发生了全局异常: {}！", ex.getMessage());
        return new ResponseEntity<>("请求出现错误！错误原因：" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
