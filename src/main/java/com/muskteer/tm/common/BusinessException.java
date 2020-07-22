package com.muskteer.tm.common;

public class BusinessException extends BaseException {
    private static final long serialVersionUID = -1657938434382769721L;
    private String[] replaceStrs = null;

    private String displayMsg = null;

    public BusinessException() {
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.displayMsg = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String messageCode, String message) {
        super(messageCode, message);
        setMessageCode(messageCode);
        this.displayMsg = message;
    }

    public BusinessException(String messageCode, String message, Throwable cause) {
        super(messageCode, message, cause);
        setMessageCode(messageCode);
        this.displayMsg = message;
    }

    public BusinessException(String messageCode, String message,
                             String[] replaceStrs) {
        super(messageCode, message);
        setReplaceStrs(replaceStrs);
        this.displayMsg = message;
    }

    public BusinessException(String messageCode, String message,
                             Throwable cause, String[] replaceStrs) {
        super(messageCode, message, cause);
        setMessageCode(messageCode);
        setReplaceStrs(replaceStrs);
        this.displayMsg = message;
    }

    public BusinessException(String message) {
        super("", message);
        this.displayMsg = message;
    }

    public String[] getReplaceStrs() {
        return this.replaceStrs;
    }

    public void setReplaceStrs(String[] replaceStrs) {
        this.replaceStrs = replaceStrs;
    }

    public String getDisplayMsg() {
        return this.displayMsg;
    }

    public void setDisplayMsg(String displayMsg) {
        this.displayMsg = displayMsg;
    }
}