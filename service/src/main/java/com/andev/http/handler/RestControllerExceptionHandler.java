package com.andev.http.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.andev.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
