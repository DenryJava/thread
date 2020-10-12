package cn.deng.blbl.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Test1 {
    public static void main(String[] args) {
        // 无界
        ConcurrentLinkedDeque<Object> deque = new ConcurrentLinkedDeque<>();
        deque.offer("张三");
        deque.offer("李四");
//        System.out.println(deque.size());
//        System.out.println(deque.poll());
//        System.out.println(deque.poll());
//        System.out.println(deque.size());
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        queue.add("张三");
        queue.add("李四");
        queue.add("王五");
        System.out.println(deque.poll());
        System.out.println(deque.poll());
    }
}
