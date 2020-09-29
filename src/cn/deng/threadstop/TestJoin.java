package cn.deng.threadstop;

// join方法
public class TestJoin implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---线程在执行:" + i);
        }
    }

    public static void main(String[] args){
        Thread thread = new Thread(new TestJoin());
        thread.start();
        for (int i = 0; i < 100; i++) {
            if(i==50){
                try {
                    thread.join(); //插队
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"---线程在执行："+i);
        }
    }
}
