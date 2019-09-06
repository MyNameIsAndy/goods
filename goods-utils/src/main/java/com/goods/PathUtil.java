package com.goods;

import java.net.URL;

/**
 * Path工具
 * @author 田帅
 *
 */
public class PathUtil {
	//得到应用目录
	//返回类路径以"/"结尾
	public static String getApplicationPath(){
		try {
			return PathUtil.getClassPath().substring(0, PathUtil.getClassPath().indexOf("WEB-INF"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 根据给定的相对路径得到这个文件的绝对URL
	 *path-配置文件所在的相对路径，此相对路径从类路径开始写，最前面不要'/',例如：configuration/deploy.properties
	 * @param path
	 * @return
	 */
	public static String getPath(String path){
		try {
			return getClassPath()+path;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	//得到类目录
	//返回类路径以"/"结尾
	public static String getClassPath(){
		URL url=null;	
		try {
			url=PathUtil.class.getResource("/");
			return url.getPath();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		
	


	}

}
