package cn.deng.threadstop;

// 测试线程优先级
public class TestPriority {
    public static void main(String[] args) {
        //主线程优先级
        System.out.println(Thread.currentThread().getName() + " ---> " + Thread.currentThread().getPriority());
        MyPriority myPriority = new MyPriority();
        Thread a = new Thread(myPriority, "a");
        Thread b = new Thread(myPriority, "b");
        Thread c = new Thread(myPriority, "c");
        Thread d = new Thread(myPriority, "d");
        Thread e = new Thread(myPriority, "e");
        Thread f = new Thread(myPriority, "f");
        Thread g = new Thread(myPriority, "g");
        a.start();
        b.setPriority(6);
        b.start();
        c.setPriority(2);
        c.start();
        d.start();
        e.start();
        f.setPriority(10);
        f.start();
        g.start();
    }
}

class MyPriority implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ---> " + Thread.currentThread().getPriority());
    }
}