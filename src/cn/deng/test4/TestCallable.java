package cn.deng.test4;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

// 实现Callable接口
public class TestCallable implements Callable<Boolean> {
    private String url;
    private String name;

    public TestCallable(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call(){
        new WebDownLoader().downLoader(url,name);
        System.out.println("下载了"+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://p.ssl.qhimg.com/dmsmty/74_74_100/t0151ebca30b273ed44.png", "1.jpg");
        TestCallable t2 = new TestCallable("https://s3m7.nzwgs.com/galileo/f1cd1510b354316dda8c2ef2ca120868.jpg", "2.jpg");
        // 创建执行任务
        ExecutorService service = Executors.newFixedThreadPool(2);
        // 提交执行
        Future<Boolean> s1 = service.submit(t1);
        Future<Boolean> s2 = service.submit(t2);
        // 获取结果
        Boolean b1 = s1.get();
        Boolean b2 = s2.get();
        // 关闭服务
        service.shutdownNow();
    }
}

class WebDownLoader{
    // 下载方法
    public void downLoader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常");
        }
    }
}