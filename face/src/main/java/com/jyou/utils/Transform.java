package com.jyou.utils;

/**
 * @describe:  将byte[] 转16进制字符串
 * @author: RenXin
 * @version: V1.0
 */
public class Transform {
    public static String returnStr(byte[] data){
        if (data == null || data.length <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < data.length; i++) {
            // 之所以用byte和0xff相与，是因为int是32位，与0xff相与后就舍弃前面的24位，只保留后8位
            String str = Integer.toHexString(data[i] & 0xff);
            if (str.length() < 2) { // 不足两位要补0
                stringBuilder.append(0);
            }
            stringBuilder.append(str);
        }
        return  stringBuilder.toString();
    }

    public static byte[] hexToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
       // String hexDigits = "0123456789abcdef";
        for (int i = 0; i < length; i++) {
            int pos = i * 2; // 两个字符对应一个byte
            int h = hexString.indexOf(hexChars[pos]) << 4; // 注1
            int l = hexString.indexOf(hexChars[pos + 1]); // 注2
            if(h == -1 || l == -1) { // 非16进制字符
                return null;
            }
            bytes[i] = (byte) (h | l);
        }
        return bytes;
    }
}
