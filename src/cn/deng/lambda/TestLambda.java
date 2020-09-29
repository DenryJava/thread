package cn.deng.lambda;

import java.util.List;

// lambda表达式
public class TestLambda {
    //静态内部类
    static class Like2 implements ILike{
        @Override
        public void lambda() {
            System.out.println("I like lambda2");
        }
    }

    public static void main(String[] args) {
        new Like().lambda();
        new Like2().lambda();
        //局部内部类
        class Like3 implements ILike{
            @Override
            public void lambda() {
                System.out.println("I like lambda3");
            }
        }
        new Like3().lambda();
        //匿名内部类
        new ILike() {
            @Override
            public void lambda() {
                System.out.println("I like lambda4");
            }
        }.lambda();
        //lambda简化
        ILike like = () -> {
            System.out.println("I like lambda5");
        };
        like.lambda();
    }
}

//定义函数式接口
interface ILike{
    void lambda();
}

//实现类
class Like implements ILike{
    @Override
    public void lambda() {
        System.out.println("I like lambda");
    }
}
