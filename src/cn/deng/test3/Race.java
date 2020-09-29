package cn.deng.test3;

// 模拟龟兔赛跑
public class Race implements Runnable{
    // 胜利者
    private static String winner;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            // 模拟兔子休息
            if("兔子".equals(Thread.currentThread().getName()) && i%10==0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"--->跑路"+i+"步");
            // 判断比赛是否结束
            if(gameOver(i)){
                break;
            }
        }
    }
    // 判断是否完成比赛
    private boolean gameOver(int steps){
        // 判断是否有胜利者
        if(winner != null){  // 已经存在胜利者
            return true;
        }{
            if(steps >= 100){
                winner = Thread.currentThread().getName();
                System.out.println(winner+"<-->赢得了比赛");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }
}
