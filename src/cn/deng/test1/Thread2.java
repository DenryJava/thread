package cn.deng.test1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

// 多线程同步下载图片
public class Thread2 extends Thread{
    private String url;
    private String name;

    public Thread2(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        new WebDownLoader().downLoader(url,name);
        System.out.println("下载了"+name);
    }

    public static void main(String[] args) {
        new Thread2("https://p.ssl.qhimg.com/dmsmty/74_74_100/t0151ebca30b273ed44.png","1.jpg").start();
        new Thread2("https://s3m7.nzwgs.com/galileo/f1cd1510b354316dda8c2ef2ca120868.jpg","2.jpg").start();
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