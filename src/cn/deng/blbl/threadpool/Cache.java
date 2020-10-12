package cn.deng.blbl.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {
    private static volatile Map<String,Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    // 读写锁
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    // 写
    public static Object put(String key,Object obj){
       try{
           w.lock();
           System.out.println("开始写入线程：key->: "+key+",val->: "+obj);
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           Object oj = map.put(key, obj);
           return oj;
       }catch (Exception e){

       }finally {
           w.unlock();
       }
        return obj;
    }
    // 读
    public static void get(String key){
        try{
            r.lock();
            System.out.println("开始读取线程：key->: "+key);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object oj = map.get(key);
            System.out.println("\tval值为: "+oj);
        }catch (Exception e){

        }finally {
            r.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Cache.put(i + "", i + "");
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Cache.get(i + "");
                }

            }
        }).start();
    }
}