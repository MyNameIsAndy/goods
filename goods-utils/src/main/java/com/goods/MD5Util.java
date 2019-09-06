package com.goods;

import java.security.MessageDigest;


public class MD5Util {
	private final static String SPKEY = "SINOCHEM-XT-2017";
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转化字节为字符
	 * 
	 * @param  bByte 需要转换的byte
	 * @return String 返回转后后String
	 */
	private static String byteToString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param  bByte 需要转换的byte数组
	 * @return String 返回转后后String
	 */
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToString(bByte[i]));
		}
		return sBuffer.toString();
	}

	/**
	 * MD5加密
	 * 
	 * @param
	 *
	 * @return String 加密后的String 机密失败或异常返回null,对密码加密时使用
	 */
	public static String encrypt(String str) {
		String returnStr = null;
		try {
			returnStr = byteToString(md5(str));
		} catch (Exception e) {
			returnStr = null;
			e.printStackTrace();
		}
		return returnStr;
	}

	/**
	 * 进行MD5加密
	 * 
	 * @param
	 *            strSrc 需要机密的字符
	 * @return byte[] 指定加密方式为md5后的byte[]
	 */

	private static byte[] md5(String strSrc) {
		byte[] returnByte = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			returnByte = md5.digest(strSrc.getBytes("UTF-8"));
			// System.out.println("MD5加密后的byte数据组后的值为(MD5加密使用UTF-8编码)："+getBytesStr(returnByte));
		} catch (Exception e) {
			returnByte = null;
			e.printStackTrace();
		}
		return returnByte;
	}
	public static void main(String[] args) {
		
		String oldString = " 毒 素发\ndd\n-#$%!<>';{}\\n\t[\\t]<>/,“， ";
		System.out.println(encrypt(oldString));
	}


}
