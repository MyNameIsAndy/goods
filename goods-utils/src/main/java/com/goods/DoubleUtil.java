package com.goods;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class DoubleUtil {

	/**
	 *
	 * @param db
	 * @return
	 */
	public static String getLong(Float db){
		long l=(long) (db/1l);
		return String.valueOf(l);
	}

	/**
	 * 不保留小数
	 * @param value1
	 * @return
	 */
	public static String getFenToYuan(String value1){
		
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal("100");
		return b1.divide(b2).toString();
	}

	/**
	 * 保留2位小数
	 * @param value1
	 * @return
	 */
	public static String getFenToYuan2(String value1){
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal("100");
		DecimalFormat df2 =new DecimalFormat("###0.00");
		return df2.format(b1.divide(b2));
	}

	public static BigDecimal getFenToYuanBgd(String value1){

		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal("100.0");
		return b1.divide(b2);
	}

	public static String formatScale(BigDecimal num){
		DecimalFormat df = new DecimalFormat("###0.00");
		return df.format(num);
	}
	
    public static long getYuanToFen(String value1){
		
	     BigDecimal b1 = new BigDecimal(value1.trim());
	     long balance = b1.movePointRight(2).longValue();
	     return balance;
	}
    
    
    public static void main(String[] args) {
    	System.out.println(getYuanToFen("8")+"");
    	System.out.println(getYuanToFen("0.30")+"");
    	System.out.println(getYuanToFen("0.3")+"");
    	System.out.println(getYuanToFen("3")+"");
    	System.out.println(getYuanToFen("3.3")+"");
    	System.out.println(getYuanToFen("3.30")+"");
    	System.out.println(getYuanToFen("36.95")+"");
    	System.out.println(getYuanToFen("38.37")+"");
		System.out.println(formatScale(getFenToYuanBgd("03")));
		System.out.println(formatScale(getFenToYuanBgd("003")));
		System.out.println(formatScale(getFenToYuanBgd("30")));
		System.out.println(formatScale(getFenToYuanBgd("300")));
		System.out.println(formatScale(getFenToYuanBgd("3689")));
	}
}
