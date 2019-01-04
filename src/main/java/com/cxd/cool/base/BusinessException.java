package com.cxd.cool.base;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1252628835498497869L;

    private int code;

    private String message;

    public BusinessException(int code, String message) {
        super(String.format("BusinessException: code:%s, message:%s", code, message));
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, Throwable e) {
        super(String.format("BusinessException: code:%s, message:%s", code, message), e);
        this.code = code;
        this.message = message;
    }

    public int getErrorcode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
