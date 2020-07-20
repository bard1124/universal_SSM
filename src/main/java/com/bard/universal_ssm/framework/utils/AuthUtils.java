package com.bard.universal_ssm.framework.utils;

import com.bard.universal_ssm.framework.constants.Constant;

import java.util.Date;
import java.util.UUID;

/**
 * 生成令牌工具类
 * 用于生成进行身份认证的唯一令牌，存入到指定时间的对象中
 */
public class AuthUtils {
	//为防止撞库，这里加盐
	private final static String SALT = Constant.SYSTEM_ID;
	
	public static String encodePassword(String oriPassword) {
		return SummaryUtils.md5sum((oriPassword + SALT).getBytes());
	}
	
	/**
	 * 生成令牌方法token
	 * @return 返回令牌字符串
	 */
	public static String genToken() {
		//调用SHA1算法生成安全签名
		String tokenStr = UUID.randomUUID().toString() + String.valueOf(new Date().getTime());
		return SummaryUtils.sha1sum(tokenStr.getBytes());
	}

	public static void main(String[] args) {
		String s = AuthUtils.encodePassword("MTIzNDU2");
		System.out.println(s);
	}
}