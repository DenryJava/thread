package cn.deng.test1;

// 继承Thread类
public class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+ "我是子线程"+i);
        }
    }

    public static void main(String[] args) {
        new Thread1().start();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"我是主线程"+i);
        }
    }
}
