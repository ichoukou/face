package com.jyou.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: RenXin
 * @Date: Created in 2018/1/17
 * @Description:
 */
public class DateUtils {
    /**
     * 默认日期格式
     */
    public static String DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * 测试主方法
     * @param args
     */
    public static void main(String[] args) {
        for(int i = 1951;i < 1960;i++){
            System.out.println(formatDate(getYearFirst(i)));
            System.out.println(formatDate(getYearLast(i)));
        }

        System.out.println(formatDate(getCurrYearFirst()));
        System.out.println(formatDate(getCurrYearLast()));

    }

    /**
     * 格式化日期
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDate(Date date){
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }

    /**
     * 获取当年的第一天
     * @param
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @param
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取明年的最后一天
     * @param
     * @return
     */
    public static Date getCurrYearLast2(){
        Calendar currCal=Calendar.getInstance();
        currCal.add(Calendar.YEAR, 1);
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }
    /**
     * 指定日期加上天数后的日期
     * @param num 为增加的天数
     * @param newDate 创建时间
     * @return
     *
     */
    public static String plusDay(int num,String newDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date  currdate = format.parse(newDate);
        System.out.println("现在的日期是：" + currdate);
        Calendar ca = Calendar.getInstance();
        ca.setTime(currdate);
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currdate = ca.getTime();
        String enddate = format.format(currdate);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }


    //当前日期加上天数：


    /**
     * 当前日期加上天数后的日期
     * @param num 为增加的天数
     * @return
     */
    public static String plusDay2(int num){

        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currdate = format.format(d)+" 23:59:59";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = format1.parse(currdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("现在的日期是：" + currdate);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d)+" 23:59:59";
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }



}
