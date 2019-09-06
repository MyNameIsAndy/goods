package com.goods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	private static final String NUMBER_PATTERN = "^[0-9]+(.[0-9]{0,2})?$";// 判断小数点后两位的数字的正则表达式
	private static final String CNUMBER_PATTERN = "^[0-9]*$";// 判断数字的正则表达式
    //判断日期（yyyy-MM-dd）的正则表达式
    private static final String DATE_PATTERN = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";


	// wi =2(n-1)(mod 11);加权因子
	final static int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,2, 1 };
	// 校验码
	final static int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
	private static int[] ai = new int[18];
	
	public static void main(String[] args) {
		System.out.println("--------->" + isDecimalNumber("100.02"));
		System.out.println("--------->" + verifyIdCard("142701200012131213"));
	}

	/**
	 * 验证是不是数字(验证到小数点后两位)
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isDecimalNumber(String number) {
		return match(NUMBER_PATTERN, number);
	}

	/**
	 * 验证是不是数字(没有小数点)
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isInteger(String number) {
		return match(CNUMBER_PATTERN, number);
	}

	/**
	 * 执行正则表达式
	 * 
	 * @param pattern
	 *            表达式
	 * @param str
	 *            待验证字符串
	 * @return 返回 <b>true </b>,否则为 <b>false </b>
	 */
	private static boolean match(String pattern, String str) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	// 校验身份证的校验码
	public static boolean verifyIdCard(String idcard) {
		if (idcard.length() == 15) {
			idcard = uptoeighteen(idcard);
		}
		if (idcard.length() != 18) {
			return false;
		}
		String verify = idcard.substring(17, 18);
		if (verify.equals(getVerify(idcard))) {
			return true;
		}
		return false;
	}

	// 15位转18位
	private static String uptoeighteen(String fifteen) {
		StringBuffer eighteen = new StringBuffer(fifteen);
		eighteen = eighteen.insert(6, "19");
		return eighteen.toString();
	}

	// 计算最后一位校验值
	private static String getVerify(String eighteen) {
		int remain = 0;
		if (eighteen.length() == 18) {
			eighteen = eighteen.substring(0, 17);
		}
		if (eighteen.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eighteen.substring(i, i + 1);
				ai[i] = Integer.valueOf(k);
			}
			for (int i = 0; i < 17; i++) {
				sum += wi[i] * ai[i];
			}
			remain = sum % 11;
		}
		return remain == 2 ? "X" : String.valueOf(vi[remain]);
    }

    /**
     * 验证字符串是不是日期
     *
     * @param date 待验证字符串
     * @author wuwl
     * @date 2018-01-26
     * @return true 是日期
     */
    public static boolean isDate(String date) {
        Pattern p = Pattern.compile(DATE_PATTERN);
        Matcher m = p.matcher(date);
        return m.find();
    }


}
