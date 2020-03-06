package com.liucccc.demo.common.exception;

import com.liucccc.demo.common.api.CommonResult;
import com.liucccc.demo.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * GlobalException
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/15 10:38
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {
    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public CommonResult bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return CommonResult.failed(Long.valueOf(e.getErrorCode()), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public CommonResult exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return CommonResult.failed(ResultCode.FAILED);
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return CommonResult.failed(e.getMessage());
    }
}
