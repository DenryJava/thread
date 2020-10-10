package cn.deng.blbl.test2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 线程中通讯， 生产者--->>消费者
public class OutInputLock {
    public static void main(String[] args) {
        Res res = new Res();
        Condition condition = res.lock.newCondition();
        Out out = new Out(res, condition);
        Input input = new Input(res, condition);
        out.start();
        input.start();
    }
}

// 共享资源
class Res {
    public String userName;
    public String sex;
    // true 生产者等待，false 生产者生产
    public boolean flag = false;
    Lock lock = new ReentrantLock();
}
// 生产者
class Out extends Thread {
    Res res;
    Condition condition;

    public Out(Res res, Condition condition) {
        this.res = res;
        this.condition = condition;
    }

    @Override
    public void run() {
        // 写的操作
        int count = 0;
        while (true) {
            try {
                // 开始上锁
                res.lock.lock();
                if (res.flag) {
                    condition.await();
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
                condition.signal();
            } catch (Exception e) {

            } finally {
                // 释放锁
                res.lock.unlock();
            }
        }
    }
}
// 消费者
class Input extends Thread {
    Res res;

    Condition condition;

    public Input(Res res, Condition condition) {
        this.res = res;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 上锁
                res.lock.lock();
                if (!res.flag) {
                    condition.await();
                }
                System.out.println(res.userName + "," + res.sex);
                res.flag = false;
                condition.signal();
            } catch (Exception e) {

            } finally {
                // 释放锁
                res.lock.unlock();
            }
        }
    }
}