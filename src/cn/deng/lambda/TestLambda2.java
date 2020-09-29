package cn.deng.lambda;

// lambda表达式简化
public class TestLambda2 {
    public static void main(String[] args) {
        ILove love = (String name) -> {
            System.out.println(name+"--");
        };
        love.love("deng1");
        //只有一个参数或参数类型都不写时可省略参数类型
        love = (name) -> {
            System.out.println(name+"--");
        };
        love.love("deng2");
        //方法体只有一条语句时可省略大括号
        love = (name) -> System.out.println(name+"--");
        love.love("deng3");
        //只有一个参数时可去掉小括号
        love = name -> System.out.println(name+"--");
        love.love("deng4");
    }
}

interface ILove{
    void love(String name);
}