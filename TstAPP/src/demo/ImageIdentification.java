package com.example.demo;

import com.example.imageRecognition.CurrentMode;
import com.example.imageRecognition.Temperature;
import com.example.imageRecognition.WhetherBoot;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class ImageIdentification {

    static int count=0;
    public static String[] GetStatus() {
            String[] status=new String[3];
            List<String[]> statusList=new ArrayList<String[]>();

            Mat mat1 = Imgcodecs.imread("C:\\Users\\18363\\Desktop\\testPic\\frame\\"+count+".jpg");//原图
            count=(count+1)%13;
            //开关机识别
            Rect rect0 = new Rect(700, 160, 150, 110);//截取开机按钮图像
            Mat roi_img0 = new Mat(mat1,rect0);
            WhetherBoot whetherBoot=new WhetherBoot();
            status[0]=whetherBoot.GetOnOrOff(roi_img0);

            if(status[0]!="Powered off!") {
                    //模式获取
                    Rect rect1 = new Rect(160, 160, 300, 130);//截取模式图像
                    Mat roi_img1 = new Mat(mat1, rect1);
                    Imgproc.cvtColor(roi_img1, roi_img1, Imgproc.COLOR_BGR2GRAY);//灰度图
                    CurrentMode currentMode = new CurrentMode();
                    status[1] = currentMode.GetMode(roi_img1);

                    //温度识别
                    Rect rect2 = new Rect(520, 180, 150, 110);//截取温度图像
                    Mat roi_img2 = new Mat(mat1, rect2);
                    Imgproc.cvtColor(roi_img2, roi_img2, Imgproc.COLOR_BGR2GRAY);//灰度
                    Temperature tem = new Temperature();
                    status[2] = tem.GetTemperature(roi_img2);
            }else {status[1]=status[2]="";}

            for(int i=0;i<status.length;i++){
                System.out.print(status[i]+"  ");
            }
            System.out.print("\n");
            //Thread.currentThread().sleep(10000);
            //System.out.print(statusList.get(3).length);
            return status;
    }
    /*static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static void main(String[] args){
            while (true) {
                    ImageIdentification.GetStatus();
            }
    }*/

}
