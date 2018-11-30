package com.bar.common.number;

import com.bar.common.exception.BusinessException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NumberUtil {

    private static DecimalFormat decimalFormatThree = new DecimalFormat("#.000");
    private static DecimalFormat decimalFormatOne = new DecimalFormat("#.0");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DecimalFormat formaterOne = new DecimalFormat();
    private static DecimalFormat formaterInt = new DecimalFormat();
    static {
        formaterInt.setMaximumFractionDigits(0);
        formaterOne.setMaximumFractionDigits(1);
        formaterOne.setGroupingSize(0);
        formaterOne.setRoundingMode(RoundingMode.FLOOR);
    }
    /**
     * 把一个double数字保留三位小数
     * 返回一个正整数
     * @param d
     * @return
     */
    public static Integer keepThreeDecimalInt(double d){
        double v = Double.parseDouble(decimalFormatThree.format(d));
        return ((Double) (v * 1000)).intValue();
    }

    /**
     * 把一个double数字保留三位小数
     * 去尾 不四舍五入
     * @param d
     * @return
     */
    public static Double keepThreeDecimalDou(double d){
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(3);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        return Double.parseDouble(formater.format(d));
    }

    /**
     * 把一个double数字保留一位小数
     * 四舍五入
     * @param d
     * @return
     */
    public static Double keepOneDecimalDouRoundOff (double d){
        return Double.parseDouble(formaterOne.format(d));
    }

    /**
     * 把一个double数字保留一位小数
     * 去尾
     * @param d
     * @return
     */
    public static Double keepOneDecimalDou(double d){
        double v = Double.parseDouble(String.format("%.1f", d));
        return v;
    }

    public static Integer keepIntDecimalDou(Double d){
        return Integer.parseInt(formaterInt.format(d));
    }

    public static Double roundUpByDouble(Double d){
        return Double.parseDouble(decimalFormatOne.format(d));
    }

    /**
     * 根据月份获取该月有多少天
     * @param month
     * @return
     */
    public static Integer getDayOfMonthByMonthNum(Integer month){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month - 1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据当前时间获取上个月有多少天
     * @return
     */
    public static Integer getDayOfLastMonthByNow(){
        Calendar c = Calendar.getInstance();
        int lastMonth = c.get(Calendar.MONTH) == 0 ? 12 : c.get(Calendar.MONTH);
        return getDayOfMonthByMonthNum(lastMonth);
    }

    /**
     * 根据字符串转换为date时间
     * @param date
     * @return
     */
    public static Date getDateByStr(String date){
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new BusinessException("格式化时间失败");
        }
    }

    /**
     * 通过年份和月份,获取该月份第一天的时间戳
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonthByYearAndMonth(Integer year, Integer month){
        return getDateByStr(year + "-" + String.format("%02d",month) + "-01");
    }

    /**
     * 根据当前时间获取上个月的第一天
     * @return
     */
    public static Date getTheFirstDayOfLastMonth(){
        Calendar c = Calendar.getInstance();
        int lastMonth = c.get(Calendar.MONTH) == 0 ? 12 : c.get(Calendar.MONTH);
        return getTheFirstDayOfCurrentMonthByMonthNum(lastMonth);
    }

    /**
     * 根据当前时间获取当前月的第一天
     * @return
     */
    public static Date getTheFirstDayOfCurrentMonth(){
        return getTheFirstDayOfCurrentMonthByMonthNum(new Date().getMonth() + 1);
    }

    /**
     * 根据月份获取当前月的第一天
     * @param month
     * @return
     */
    public static Date getTheFirstDayOfCurrentMonthByMonthNum(Integer month){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month - 1);
        int year = c.get(Calendar.YEAR);
        int monthNum = c.get(Calendar.MONTH) + 1;
        return getDateByStr(year + "-" + String.format("%02d", monthNum) + "-1" );
    }

    /**
     * 根据当前时间获取上个月的年份
     * @return
     */
    public static Integer getLastYearNumCurrentTime(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, - 1);
        return c.get(Calendar.YEAR);
    }

    /**
     * 根据当前时间获取上个月的月份
     * @return
     */
    public static Integer getLastMonthNumCurrentTime(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, - 1);
        return c.get(Calendar.MONTH);
    }
    private static BigDecimal num = new BigDecimal("0");
    public static void main(String[] args) throws ParseException {
        System.out.println(num.intValue());
    }

}
