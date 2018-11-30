package com.bar.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DateUtile {
    private static final HashMap<String, SimpleDateFormat> formats = new HashMap<>();
    public static final String _YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String _YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String _YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String _YYYY_MM_DD = "yyyy-MM-dd";
    public static final String _HHMMSS = "HH:mm:ss";

    public static final SimpleDateFormat _SDF_YYYY_MM_DD = new SimpleDateFormat(_YYYY_MM_DD);
    public static final SimpleDateFormat _SDF_HHMMSS = new SimpleDateFormat(_HHMMSS);
    public static final SimpleDateFormat _SDF_YYYYMMDDHHMMSS = new SimpleDateFormat(_YYYYMMDDHHMMSS);
    public static final SimpleDateFormat _SDF_YYYYMMDDHHMMSSSSS = new SimpleDateFormat(_YYYYMMDDHHMMSSSSS);
    public static final SimpleDateFormat _SDF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(_YYYY_MM_DD_HH_MM_SS);
    private static AtomicInteger atomicInteger = new AtomicInteger(10000000);//自增从10000000开始

    public static String getThisDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public static String defaultFormat(long date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String defaultFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String trim(long date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    public static String trim(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    private static SimpleDateFormat getFormat(String formatStr) {
        SimpleDateFormat format = formats.get(formatStr);
        if (format == null) {
            format = new SimpleDateFormat(formatStr);
            formats.put(formatStr, format);
        }
        return format;
    }

    public static String getFormatDate(long milliseconds, String format) {
        Date date = new Date(milliseconds);
        return getFormatDateTime(date, format);
    }

    public static String getFormatCurrentTime(String format) {
        return getFormatDateTime(new Date(), format);
    }

    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static long getFormatTime(String dstr, String format) {
        return getFormatDate(dstr, format).getTime();
    }

    public static Date getFormatDate(String dstr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        return getFormat(format).format(date);
    }

    public static Date parse(String str, String format) {
        try {
//            if (TextUtils.isEmpty(str)) {
//                return null;
//            }
            return getFormat(format).parse(str);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 格式化一个只有是日期的字符串
     *
     * @return
     */
    public static String _yyyy_mm_dd() {
        return _SDF_YYYY_MM_DD.format(new Date());
    }

    /**
     * 格式化一个只有是时间的字符串
     *
     * @return
     */
    public static String _hh_mm_ss() {

        return _SDF_HHMMSS.format(new Date());
    }

    /**
     * 格式化一个只有是时间的字符串
     *
     * @return
     */
    public static String _yyyy_mm_dd_hh_mm_ss(Date date) {
        return _SDF_YYYY_MM_DD_HH_MM_SS.format(date);
    }

    /**
     * 生成8位日期+6位时间的字符串
     *
     * @param date
     * @return
     */
    public static String _yyyymmddhhmmss(Date date) {
        return _SDF_YYYYMMDDHHMMSS.format(date);
    }

    /**
     * 生成8位日期+9位时间的字符串
     *
     * @param date
     * @return
     */
    public static String _YYYYMMDDHHMMSSSSS(Date date) {
        return _SDF_YYYYMMDDHHMMSSSSS.format(date);
    }

    /**
     * 创建不连续的编号
     * <p>
     * TRANSEQNO编号
     *
     * @return 唯一的、不连续TRANSEQNO编号
     */
    public static synchronized String getTRANSEQNOByUUID() {
        Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
        if (uuidHashCode < 0) {
            uuidHashCode = -uuidHashCode;
        }
        String date = _SDF_YYYYMMDDHHMMSSSSS.format(new Date());
        return date + uuidHashCode;
    }

    /**
     * 获取同一毫秒 生成的随机号连续
     *
     *            数据中心编号
     * @return 同一秒内连续的编号
     */
    public static synchronized String getUuidByAtomic() {
        atomicInteger.getAndIncrement();
        int i = atomicInteger.get();
        String date = _YYYYMMDDHHMMSSSSS(new Date());
        return date + i;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 10000000; i++) {
            set.add(getTRANSEQNOByUUID());
        }
        long end = System.currentTimeMillis();
        System.out.println(set.size());
        System.out.println(end-start);
    }
}
