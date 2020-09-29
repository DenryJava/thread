package cn.deng.test2;

// 实现Runnable接口
public class Thread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName()+"我是子线程"+i);
        }
    }

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName()+"我是主线程"+i);
        }
    }
}
