package cn.deng.blbl.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//线程池应用
public class Test1 {
    public static void main(String[] args) {
        // 可缓存线程池  可以重复利用
//        ExecutorService service = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            int temp = i;
//            service.execute(() -> System.out.println("ThreadName"+Thread.currentThread().getName()+"，i=："+temp));
//        }
        // 可固定长度线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            int temp = i;
            service.execute(() -> System.out.println("ThreadName"+Thread.currentThread().getName()+"，i=："+temp));
        }
        // 可定时线程池
//        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
//        for (int i = 0; i < 10; i++) {
//            int temp = i;
//            service.schedule(() -> System.out.println("ThreadName"+Thread.currentThread().getName()+"，i=："+temp),3,TimeUnit.SECONDS);
//        }
        // 单线程
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 10; i++) {
//            int temp = i;
//            service.execute(() -> System.out.println("ThreadName"+Thread.currentThread().getName()+"，i=："+temp));
//        }
        // 停止
        service.shutdown();
    }
}
