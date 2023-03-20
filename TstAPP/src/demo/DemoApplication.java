package com.example.demo;

import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DemoApplication.class, args);
/*        Boolean result = false;
        while(!result) {
            try {
                SpringApplication.run(DemoApplication.class, args);
                Thread.sleep(20 * 1000); //设置暂停的时间 10 秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }
}