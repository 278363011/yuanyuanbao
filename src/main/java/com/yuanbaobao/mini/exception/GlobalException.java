package com.codebaobao.exception;


import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class GlobalException {




    @ExceptionHandler(UserNameAndPwdErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<CodeMsg> handlerUserNameAndPwdErrorException(final UserNameAndPwdErrorException ex) {
        return Result.error(CodeMsg.create(10000, ex.getMessage()));
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<CodeMsg> handlevalidException(final ConstraintViolationException e) {
        return Result.error(CodeMsg.create(10001, e.getMessage()));
    }

    @ExceptionHandler(value = BindException.class)
    public Object bindException(final BindException ex) {
        final BindingResult bindingResult = ex.getBindingResult();
        // 获取所有的错误信息
        final List<ObjectError> allErrors = bindingResult.getAllErrors();
        // 输出
        final StringBuilder stringBuilder = new StringBuilder();
        allErrors.forEach(e -> stringBuilder.append(e.getDefaultMessage()).append("|"));
        return Result.error(CodeMsg.create(10002, stringBuilder.toString()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        final BindingResult bindingResult = ex.getBindingResult();
        // 获取所有的错误信息
        final List<ObjectError> allErrors = bindingResult.getAllErrors();
        // 输出
        final StringBuilder stringBuilder = new StringBuilder();
        allErrors.forEach(e -> stringBuilder.append(e.getDefaultMessage()).append("|"));
        return Result.error(CodeMsg.create(10003, stringBuilder.toString()));
    }

}
