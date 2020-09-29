package cn.deng.proxy;

// 静态代理模式
public class StaticProxy {
    public static void main(String[] args) {
        new WeddingCompay(new You()).happyMarry();
    }
}

interface Marry{
    void happyMarry();
}
// 真实角色
class You implements Marry{
    @Override
    public void happyMarry() {
        System.out.println("我们结婚了");
    }
}
// 代理角色
class WeddingCompay implements Marry {
    private Marry marry;

    public WeddingCompay(Marry marry) {
        this.marry = marry;
    }

    @Override
    public void happyMarry() {
        before();
        marry.happyMarry();
        after();
    }

    private void before() {
        System.out.println("结婚前，布置现场。。。");
    }

    private void after() {
        System.out.println("结婚后，收尾款。。。");
    }
}