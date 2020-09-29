package cn.deng.threadstop;

// 线程礼让
public class TestYield{
    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield,"小明").start();
        new Thread(myYield,"小王").start();
    }
}

class MyYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---线程开始执行。。。");
        Thread.yield(); //线程礼让
        System.out.println(Thread.currentThread().getName() + "---线程停止执行。。。");
    }
}
