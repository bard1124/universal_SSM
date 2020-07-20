package com.bard.universal_ssm.framework.exception;

public class TokenException extends Exception {
	private static final long serialVersionUID = -846907076546271229L;
	
	private final static String MESSAGE = "token验证失败";

	public TokenException() {
        super(MESSAGE);
    }

	public TokenException(String msg) {
        super(msg);
    }

	public TokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

	public TokenException(Throwable cause) {
        super(cause);
    }
}
