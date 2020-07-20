package com.bard.universal_ssm.framework.exception;

import java.util.List;

public class ValidationException extends Exception {
	private static final long serialVersionUID = -1362795949333317008L;
	
	private final static String MESSAGE = "数据验证失败";
	
	private final List<String> messageList;

	public ValidationException(List<String> messageList) {
        super(MESSAGE);
        this.messageList = messageList;
    }

	public List<String> getMessageList() {
		return messageList;
	}
}
