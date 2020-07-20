package com.bard.universal_ssm.framework.exception;

public class BusinessException extends Exception{
	private static final long serialVersionUID = -4037990940448444380L;
	
	private final static String MESSAGE = "业务验证失败";

	public BusinessException() {
        super(MESSAGE);
    }

	public BusinessException(String msg) {
        super(msg);
    }

	public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

	public BusinessException(Throwable cause) {
        super(cause);
    }
}
