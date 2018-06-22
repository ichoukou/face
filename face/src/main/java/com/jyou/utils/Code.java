package com.jyou.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Created with IntelliJ IDEA.
 * @User: YanBangQing
 * @Date: 2018/1/11
 * @Time: 14:06
 * @ProjectName:card_center
 * @Comment: 生成员工编号
 */
public class Code {
    /**
     * 年月日
     * 例：180111001（18年01月11日001号）
     */
    public final static String EmpCode() {
        String dateStr = "";
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyMMdd");
        dateStr = sdf.format(date);
        System.out.println(dateStr);
        return dateStr;
    }

    public static void main(String[] args) {
       //测试函数
    }
}
