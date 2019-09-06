package com.goods;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.CRC32;

/**
 * 提供CRC32签名的方法
 * @author apple
 * @date 2018-01-11
 */
@Component
public class CRCUtil {

    /**
     * 得到一个字符串的CRC32加密后的数据
     * @param content
     * @return
     * @author apple
     * @date 2018-01-11
     */
    public String encrypt(String content){
        CRC32 crc32 = new CRC32();
        crc32.update(content.getBytes());
        return crc32.getValue()+"";
    }

    /**
     * 得到一个字符串和密钥的CRC32加密后的数据
     * @param content
     * @param key
     * @return
     * @author apple
     * @date 2018-01-11
     */
    public String encrypt(String content,String key){
        CRC32 crc32 = new CRC32();
        crc32.update((content+key).getBytes());
        return crc32.getValue()+"";
    }

    /**
     * 得到一个大文件的CRC32加密后的数据
     * @param file
     * @return
     * @author apple
     * @date 2018-01-11
     */
    public String encrypt(File file) {
        CRC32 crc32 = new CRC32();
        //MessageDigest.get
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                crc32.update(buffer,0, length);
            }
            return crc32.getValue()+"";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        CRCUtil util = new CRCUtil();
        System.out.println(util.encrypt("我的app12312312"));
    }

}
