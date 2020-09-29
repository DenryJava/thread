package cn.deng.sny;

// 不安全买票
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"小明").start();
        new Thread(buyTicket,"小王").start();
        new Thread(buyTicket,"小黄牛").start();
    }
}

class BuyTicket implements Runnable{
    // 票
    private int ticket = 10;
    private boolean flag = true; //外部停止方式

    @Override
    public void run() {
        while(flag){
            buy();
        }
    }

    private void buy(){
        // 判断是否有票
        if(ticket <= 0){
            flag=false;
            return;
        }
        //模拟延迟
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"购买到第["+ticket--+"]张票");
    }
}
