package com.jyou.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Author: RenXin
 * @Date: Created in 2018/1/29
 * @Description:
 */
public class SignUtils {
    private static final Logger logger = LoggerFactory.getLogger(SignUtils.class);

    /**
     * 验证消息是否为合法消息
     * @param params 参数数组
     * @param key 接口密钥
     * @return 验证结果
     */
    public static boolean verify(Map<String, String> params, String key) {

        String mysign = buildMysign(params,key);
        String sign = null;
        if(params.get("sign") != null) {sign = params.get("sign");}
        if (mysign.equals(sign)) {
            return true;
        } else {
            logger.error("本地签名："+mysign + " 远程签名：" + sign);
            System.out.println("本地签名："+mysign + " 远程签名：" + sign);
            return false;
        }
    }

    /**
     * 生成签名结果
     * @param sArray 要签名的数组
     * @param key 通知密钥
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, String> sArray,String key) {
        Map<String, String> result = paraFilter(sArray);
        String prestr = createLinkString(result); //把数组所有元素，按照“参数参数值”的模式拼接成字符串
        prestr = key + prestr; //把接口密钥+拼接后的字符串直接连接起来
        // String mysign = DigestUtils.md5DigestAsHex(prestr.getBytes());
        //String test = "piaostcardCustoms{\"Custom\":[{\"areaId\":1,\"numday\":10,\"numtotle\":100},{\"areaId\":1,\"numday\":10,\"numtotle\":100}]}creatCardMap{\"brokerage\":\"3.50\",\"creator\":\"管理员\",\"time_type\":\"year\",\"card_price\":\"200\",\"card_type\":\"普通年卡B\",\"period_validity\":\"1\",\"card_desc\":\"nice\",\"picture\":\"/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxITEhUSEhIVFRUXFRUXFxcYFRUVFRUXFxUXFhUVFxUYHSggGBolHRUXITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQFy0dHx4tLS0tLS0tLS0tLS";
       // boolean b = prestr.equals(test);

        String mysign = Md5Tools.MD5(prestr);
        //String s = Md5Tools.MD5(test);
        if (mysign != null) {
            mysign = mysign.toUpperCase();
        }
        return mysign;
    }

    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数参数值”的模式拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
           /* if (value.length()>300){
                prestr = prestr + key + value.substring(0,300);
            }else{*/
                prestr = prestr + key + value;
          /*  }*/

        }
        return prestr;
    }
}
