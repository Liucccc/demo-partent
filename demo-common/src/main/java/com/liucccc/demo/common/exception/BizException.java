package com.liucccc.demo.common.exception;

import com.liucccc.demo.common.api.IErrorCode;

/**
 * BizException
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/15 10:28
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected long errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(IErrorCode errorCode) {
        super(String.valueOf(errorCode.getCode()));
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMessage();
    }

    public BizException(IErrorCode errorCode, Throwable cause) {
        super(String.valueOf(errorCode.getCode()), cause);
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(long errorCode, String errorMsg) {
        super(String.valueOf(errorCode));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(long errorCode, String errorMsg, Throwable cause) {
        super(String.valueOf(errorCode), cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
