package com.goods.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 
 * @author tianshuai
 *
 */
public class FileUtil {
	
	public static void toFile(String info,String fileAllPath,String charsetName,boolean append) {
		
		if(fileAllPath!=null&&!fileAllPath.trim().equals("")){
			try {
				mkFile(fileAllPath);
				toFile(info, new File(fileAllPath),charsetName,append);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void toFile(String info,File file,String charsetName,boolean append) {
		if(charsetName==null||"".equals(charsetName.trim())) charsetName="utf-8";
		FileOutputStream out = null;
            try {
           	 out=new FileOutputStream(file,true);        
           	 StringBuffer sb=new StringBuffer();
           	 sb.append(info!=null?info:"");
           	 out.write(sb.toString().getBytes(charsetName));
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
					try {
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
		   }
	}
	public static void mkFile(String fileAllPaht) throws Exception{
		File file=new File(fileAllPaht);
		File parentDir=new File(file.getParent());
		if(!file.exists()){
			mkDir(parentDir);
		}
		if(!file.exists()){
			mkFile(file);
		}
	}
	public static void mkDir(File file){
		if(!file.exists()){
			file.mkdirs();
		}
	}
	public static void mkFile(File file) throws Exception{
		if(!file.exists()){
			file.createNewFile();
		}
	}
	//删除文件
	public static void delFile(File file){
       if(file.exists()&&file.isFile())
            file.delete();
	}


    public static void copyFile(File source, File dest)
			throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return true\false
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录中的子目录下
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}


}
