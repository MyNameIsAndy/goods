package com.goods;

import java.util.Random;
import java.util.UUID;
import java.util.zip.CRC32;

/**
 * Created by apple on 2017/8/4.
 */
public class RandomUtil {
    /**
     * 获取32位UUID
     *
     * @return
     */
    public static String get32UUID() {
        String currentDate = DateTimeUtil.getSystemDate(11);
        return currentDate+UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取32位UUID
     *
     * @return
     */
    public static String get32UUIDNoDate() {
        String currentDate = DateTimeUtil.getSystemDate(11);
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取36位UUID
     *
     * @return
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成四位随机数字
     *
     * @return
     */
    public static int get4Number() {
        Random random = new Random();//实例化一个random的对象ne
        return random.nextInt(9999 - 1000 + 1) + 1000;//为变量赋随机值1000-9999
    }
    public static String getSerialNo(){
        String currentDate = DateTimeUtil.getSystemDate(11);
        CRC32 crc32 = new CRC32();
        crc32.update(UUID.randomUUID().toString().getBytes());
        return "20"+currentDate+crc32.getValue();
    }
    public static void main(String[] args) {
        System.out.println(get4Number());
    }

    /**
     * 获取指定长度随机字符
     *
     * @param length 长度
     * @return 指定长度随机字符
     */
    public static String getRandomChar(int length) {
        String randomString = "";
        if (length > 0) {
            /*随机数函数*/
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                /*生成36以内的随机数，不含36，并转化为36位*/
                randomString += Integer.toString(random.nextInt(36), 36);
            }
        }
        return randomString;
    }
}
