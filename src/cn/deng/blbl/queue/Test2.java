package cn.deng.blbl.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//生产者
class ProducerThread extends Thread {
    private BlockingQueue queue;
    private volatile boolean flag = true;
    //原子类
    private static AtomicInteger count = new AtomicInteger();

    public ProducerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("生产者线程启动。。。");
        try {
            while (flag) {
                // 添加队列
                boolean offer = queue.offer(count.incrementAndGet());
                if (offer) {
                    System.out.println("生产者添加队列成功...");
                } else {
                    System.out.println("生产者添加队列失败...");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("\t生产者线程停止");
        }
    }

    public void stopThread() {
        flag = false;
    }
}
// 消费者
class ConsummerThread extends Thread {
    private BlockingQueue queue;
    private volatile boolean flag = true;

    public ConsummerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("消费者线程启动。。。");
        try {
            while (flag) {
                // 获取队列数据
                Object poll = queue.poll(2, TimeUnit.SECONDS);
                if (poll != null) {
                    System.out.println("消费者获取队列数据，消费成功--->>> " + poll);
                } else {
                    System.out.println("队列中无数据");
                    flag = false;
                }
            }
        } catch (Exception e) {
            System.out.println("\t消费者线程停止");
        }
    }
}
// 队列
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new LinkedBlockingQueue(10);
//        ProducerThread p1 = new ProducerThread(queue);
        ProducerThread p2 = new ProducerThread(queue);
        ConsummerThread c1 = new ConsummerThread(queue);
//        p1.start();
        p2.start();
        c1.start();
        // 执行10秒
        Thread.sleep(1000 * 10);
//        p1.stopThread();
        p2.stopThread();
    }
}
