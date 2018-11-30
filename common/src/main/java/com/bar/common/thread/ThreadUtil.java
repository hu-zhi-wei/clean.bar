package com.bar.common.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {

    /** 核心线程数量 */
    private static final Integer corePoolSize = 5;
    /** 最大程数量 */
    private static final Integer maximumPoolSize = 20;
    /** 闲置线程存活时间 */
    private static final Long keepAliveTime = 30L;
    /** 初始化工作线程 */
    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(5);

    /**
     * 新建一个线程池
     * @return
     */
    public static ThreadPoolExecutor newThreadPool(){
        return new ThreadPoolExecutor( corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS,
                workQueue );
    }
}
