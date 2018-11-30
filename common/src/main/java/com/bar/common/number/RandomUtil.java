package com.bar.common.number;

import com.bar.common.date.DateUtile;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomUtil {
    
    private static StringBuilder builder = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private static Random random = new Random();
    private static IdWorker idWorker = new IdWorker(2);
    private static AtomicInteger atomicInteger = new AtomicInteger(1000000);
    /**
     * 获取一个随机UUID
     * @return
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder(uuid.replace("-", ""));

        for (int i = 0; i < 4; i++) {
            sb.append(builder.charAt(random.nextInt(builder.length())));
        }
        return sb.toString();
    }

    public static String getMd5(String salt, String password){
        String hashAlgorithName = "MD5";
        int hashIterations = 1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        return obj.toString();
    }

    public static synchronized String createContinuationUuid(){
        String date = DateUtile._YYYYMMDDHHMMSSSSS(new Date());
        return date + idWorker.nextId();
    }

    /**
     * 获取同一秒钟 生成的订单号连续
     *
     *            数据中心编号
     * @return 同一秒内订单连续的编号
     */
    public static synchronized String getOrderNoByAtomic() {
        atomicInteger.getAndIncrement();
        int i = atomicInteger.get();
        String date = DateUtile._YYYYMMDDHHMMSSSSS(new Date());
        return date + i;
    }

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 10000000; i++) {
            String uuid = getOrderNoByAtomic();
            set.add(uuid);
        }

        System.out.println(set.size());
    }
}
