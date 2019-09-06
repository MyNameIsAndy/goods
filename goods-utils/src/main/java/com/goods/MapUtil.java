package com.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tianshuai
 *
 */
public class MapUtil {
	
	/**
	 * 
	 * @param resultMap
	 * @param key
	 * @return
	 */
	public static String getValue(Map<String,?> resultMap,String key,String nullStr) {
		Object valueObj=resultMap.get(key);
		if(valueObj!=null){
			return valueObj.toString();
		}else{
			return nullStr;
		}
	}
	
	public static ArrayList<Map<String,String>> getBatchValue(Map<String,?> resultMap,String key, Object obj) {
		Object valueObj=resultMap.get(key);
		if(valueObj!=null && valueObj instanceof List ){
			return  (ArrayList<Map<String,String>>)valueObj;
		}else{
			return (ArrayList<Map<String,String>>)valueObj;
			
		}
	}
	public static ArrayList<String> getListValue(Map<String,?> resultMap,String key, Object obj) {
		Object valueObj=resultMap.get(key);
		if(valueObj!=null){
			return  (ArrayList<String>)valueObj;
		}else{
			return (ArrayList<String>)valueObj;
			
		}
	}
	public static Map<String,String> getMapValue(Map<String,?> resultMap,String key, Object obj) {
		Object valueObj=resultMap.get(key);
		if(valueObj!=null){
			return  (Map<String,String>)valueObj;
		}else{
			return (Map<String,String>)obj;
			
		}
	}
	
 public static ArrayList<Map<String,Object>> getTradeListValue(Map<String,?> resultMap,String key, Object obj) {
		Object valueObj=resultMap.get(key);
		if(valueObj!=null && valueObj instanceof List ){
			return  (ArrayList<Map<String,Object>>)valueObj;
		}else{
			return (ArrayList<Map<String, Object>>) obj;
			
		}
	}
	
 public static void main(String[] args) {
	Map<String,Object> resulMap = new HashMap<String,Object>();
	resulMap.put("tradeList","abc");
	
	ArrayList<Map<String,Object>> resul = MapUtil.getTradeListValue(resulMap,"tradeList",null);
	System.out.println(resul);
 }
}
