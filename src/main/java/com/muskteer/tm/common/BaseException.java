package com.muskteer.tm.common;

import java.util.List;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -6765360320533958383L;
    private String messageCode;
    private List<BaseException> throwsList;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String messageCode, String message) {
        super(message);
        setMessageCode(messageCode);
    }

    public BaseException(String messageCode, String message, Throwable cause) {
        super(message, cause);
        setMessageCode(messageCode);
    }

    public BaseException(List<BaseException> lst) {
        this.throwsList = lst;
    }

    public List<BaseException> getThrowsList() {
        return this.throwsList;
    }

    public String getMessageCode() {
        return this.messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}