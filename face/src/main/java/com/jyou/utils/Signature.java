package com.jyou.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created with IntelliJ IDEA.
 * @User: YanBangQing
 * @Date: 2018/2/5
 * @Time: 16:33
 * @ProjectName:card_center
 * @Comment:
 */
public class Signature {

    public static boolean checkSign(Map<String,Object> map){
        Map<String,String> validate = new HashMap<>();
        String mySign = "piaostcard";
        try {
            for (String key : map.keySet()) {
                String value = String.valueOf(map.get(key));
                validate.put(key, value);
            }


//            validate.put("id",Jsonutils.objectToJson(map.get("id")));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        Map<String,String> validate1 = SignUtils.paraFilter(validate);


        return SignUtils.verify(validate, mySign);
    }



}
