package com.bard.universal_ssm.framework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SummaryUtils {
	private static String sum(byte[] data, String algorithm) {
	    if (data.length == 0){
	        return null;
	    }
	    try {
	        MessageDigest mdSum = MessageDigest.getInstance(algorithm);
	        byte[] md = mdSum.digest(data);
	        return md5String(md);
	    } catch (NoSuchAlgorithmException e) {
	    	//如果异常了，请检查jdk问题
	    }
		return null;
	}
	
    /**
     * 将md5的byte流转换成字符串
     * @param md
     * @return
     */
    public static String md5String(byte[] md) {
    	char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int j = md.length;
        char[] buf = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte b = md[i];
            buf[k++] = hexDigits[b >>> 4 & 0xf];
            buf[k++] = hexDigits[b & 0xf];
        }
        return new String(buf);
    }
	
	/**
	 * md5加密
	 * @param data
	 * @return
	 */
	public static String md5sum(byte[] data) {
		return sum(data, "MD5");
	}
	
	/**
	 * sha1加密
	 * @param data
	 * @return
	 */
	public static String sha1sum(byte[] data) {
		return sum(data, "SHA1");
	}
}
