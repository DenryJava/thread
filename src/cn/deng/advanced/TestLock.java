package cn.deng.advanced;

import java.util.concurrent.locks.ReentrantLock;

// 测试lock
public class TestLock {
    public static void main(String[] args) {
        TestLock2 lock2 =  new TestLock2();
        new Thread(lock2,"小明").start();
        new Thread(lock2,"小王").start();
        new Thread(lock2,"小黄牛").start();
    }
}

class TestLock2 implements Runnable{

    int tick = 10;
    // 定义lock锁 , 可重入锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try {
                lock.lock(); //加锁
                if(tick <= 0)break;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"购买了第["+tick--+"]张票");
            }finally {
                lock.unlock(); //解锁
            }
        }
    }
}