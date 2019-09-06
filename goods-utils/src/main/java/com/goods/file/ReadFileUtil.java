package com.goods.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ReadFileUtil {
	
	/**
	 * 读取文件内容返回字符串
	 * 经测试读取512M文件耗时378394ms
	 * @param fileName
	 */
	public static String readFileByStream(String fileName){
			return readFileByStream(new File(fileName));
	}

	/**
	 * 读取文件内容返回字符串
	 * 经测试读取512M文件耗时378394ms
	 * @param file
	 */
	public static String readFileByStream(File file){
//		long start = System.currentTimeMillis();
		FileInputStream inputStream = null;
		Scanner sc = null;
		StringBuffer buffer = new StringBuffer();
		try {
			inputStream = new FileInputStream(file);
			sc = new Scanner(inputStream, "UTF-8");
			String line = "";
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				buffer.append(line);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}
		}
//		long end = System.currentTimeMillis();
//		System.out.println("读取文件内容花费：" + (end - start) + "毫秒");
		return buffer.toString();
	}
}
