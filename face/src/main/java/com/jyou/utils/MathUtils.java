package com.jyou.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: RenXin
 * @Date: Created in 2018/2/2
 * @Description:   object转bigdecimal 工具类
 */
public class MathUtils {
    public static BigDecimal getBigDecimal(Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }
}
