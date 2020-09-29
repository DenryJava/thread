package cn.deng.test3;

// 买火车票
public class BuyTrain implements Runnable {
    // 票数
    private int poll = 10;

    @Override
    public void run() {
        while (true) {
            if (poll <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--拿到了第[" + poll-- + "]张票");
        }
    }

    public static void main(String[] args) {
        BuyTrain buyTrain = new BuyTrain();
        new Thread(buyTrain, "张三").start();
        new Thread(buyTrain, "李四").start();
        new Thread(buyTrain, "黄牛党").start();
    }
}
