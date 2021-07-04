package com.yuanbaobao.mini.exception;


import com.yuanbaobao.mini.result.CodeMsg;
import com.yuanbaobao.mini.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = WxApiException.class)
    @ResponseBody
    public Result<String> jsonErrorHandler(HttpServletRequest req, WxApiException e) throws Exception {
        log.error("微信api访问异常！原因是:",e);
        return Result.error(CodeMsg.create(500, "微信api访问异常！"));
    }
    @ExceptionHandler(value = TokenException.class)
    @ResponseBody
    public Result<String> tokenErrorHandler(HttpServletRequest req, WxApiException e) throws Exception {
        log.error("token异常！原因是:",e);
        return Result.error(CodeMsg.create(500, "微信服务器异常！"));
    }
    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return Result.error(CodeMsg.create(500, "未知异常！"));
    }


//    @ExceptionHandler(value = {ConstraintViolationException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<CodeMsg> handlevalidException(final ConstraintViolationException e) {
//        return Result.error(CodeMsg.create(10001, e.getMessage()));
//    }
//
//    @ExceptionHandler(value = BindException.class)
//    public Object bindException(final BindException ex) {
//        final BindingResult bindingResult = ex.getBindingResult();
//        // 获取所有的错误信息
//        final List<ObjectError> allErrors = bindingResult.getAllErrors();
//        // 输出
//        final StringBuilder stringBuilder = new StringBuilder();
//        allErrors.forEach(e -> stringBuilder.append(e.getDefaultMessage()).append("|"));
//        return Result.error(CodeMsg.create(10002, stringBuilder.toString()));
//    }
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Object methodArgumentNotValidException(final MethodArgumentNotValidException ex) {
//        final BindingResult bindingResult = ex.getBindingResult();
//        // 获取所有的错误信息
//        final List<ObjectError> allErrors = bindingResult.getAllErrors();
//        // 输出
//        final StringBuilder stringBuilder = new StringBuilder();
//        allErrors.forEach(e -> stringBuilder.append(e.getDefaultMessage()).append("|"));
//        return Result.error(CodeMsg.create(10003, stringBuilder.toString()));
//    }

}
