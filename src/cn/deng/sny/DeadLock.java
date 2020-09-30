package cn.deng.sny;

// 死锁:多个线程互相抱着对方需要的资源形成僵持
public class DeadLock {
    public static void main(String[] args) {
        Makeup m1 = new Makeup(0,"test");
        Makeup m2 = new Makeup(1,"aaa");
        m1.start();
        m2.start();
    }
}

// 口红
class Lipstick{}

// 镜子
class Mirror{}

class Makeup extends Thread {
    //需要的资源只有一份,用 static 来保证资源只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice; // 选择
    String girlName; // 使用化妆品的人

    Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 化妆,互相持有对方的锁
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) { //获得口红的锁
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(1000);
                synchronized (mirror) { // 一秒钟后获得镜子的锁
                    System.out.println(this.girlName + "获得镜子的锁");
                }
            }
            // 将锁提出来解决死锁问题，锁用完立即释放了，所以可拿到对方释放的锁
//            synchronized (mirror) { // 一秒钟后获得镜子的锁
//                System.out.println(this.girlName + "获得镜子的锁");
//            }
        }else{
            synchronized (mirror) { //获得镜子的锁
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(2000);
                synchronized (lipstick) { // 一秒钟后获得口红的锁
                    System.out.println(this.girlName + "获得口红的锁");
                }
            }
            // 将锁提出来解决死锁问题，锁用完立即释放了，所以可拿到对方释放的锁
//            synchronized (lipstick) { // 一秒钟后获得口红的锁
//                System.out.println(this.girlName + "获得口红的锁");
//            }
        }
    }
}