package cn.deng.blbl.test1;

// 线程中通讯， 生产者--->>消费者
public class OutInputThread {
    public static void main(String[] args) {
        Res res = new Res();
        Out out = new Out(res);
        Input input = new Input(res);
        out.start();
        input.start();
    }
}

// 共享资源
class Res{
    public String userName;
    public String sex;
    // true 生产者等待，false 生产者生产
    public boolean flag = false;
}
// 生产者
class Out extends Thread {
    Res res;

    public Out(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        // 写的操作
        int count = 0;
        while (true) {
            synchronized (res){
                if(res.flag){
                    try {
                        res.wait(); // 释放锁资源
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count == 0) {
                    res.userName = "小红";
                    res.sex = "女";
                } else {
                    res.userName = "余胜军";
                    res.sex = "男";
                }
                // 计算奇数或者偶数
                count = (count + 1) % 2;
                res.flag = true;
                res.notify(); // 唤醒等待的线程
            }
        }
    }
}
// 消费者
class Input extends Thread {
    Res res;

    public Input(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (res) {
                if (!res.flag) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(res.userName + "," + res.sex);
                res.flag = false;
                res.notify(); // 唤醒等待的线程
            }
        }
    }
}