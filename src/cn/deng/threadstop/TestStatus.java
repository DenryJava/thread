package cn.deng.threadstop;

// 观察线程状态
public class TestStatus {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "我是子线程：" + i);
            }
        });
        Thread.State state = thread.getState();
        //启动前状态
        System.out.println(state);
        //启动后状态
        thread.start();
        state = thread.getState();
        System.out.println(thread.getState());

        while(thread.getState() != Thread.State.TERMINATED){  //判断线程是否为终止,只要线程不终止就一直输出状态
            Thread.sleep(100);
            state = thread.getState(); //更新线程状态
            System.out.println(state);
        }
    }
}
