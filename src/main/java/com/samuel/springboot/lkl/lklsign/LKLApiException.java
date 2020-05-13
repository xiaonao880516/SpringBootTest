/**
 * lakala.com Inc.
 * Copyright (c) 2005-2016 All Rights Reserved.
 */
package com.samuel.springboot.lkl.lklsign;


/**
 * 
 * @author Alan
 */
public class LKLApiException extends Exception {

    private static final long serialVersionUID = -238091758285157331L;

    private String            errCode;
    private String            errMsg;

    public LKLApiException() {
        super();
    }

    public LKLApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public LKLApiException(String message) {
        super(message);
    }

    public LKLApiException(Throwable cause) {
        super(cause);
    }

    public LKLApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}