package com.example.imageRecognition;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class WhetherBoot {

    public String GetOnOrOff(Mat src){
        String OnOrOff = "";
        Mat hsv_image = new Mat(src.size(), src.type());
        Imgproc.cvtColor(src, hsv_image, Imgproc.COLOR_BGR2HSV);//HSV图片
        int hsv_image_rows = hsv_image.rows();//行数
        int hsv_image_colums = hsv_image.cols();//列数
        for (int i = 0; i < hsv_image_rows; i++) {
            for (int j = 0; j < hsv_image_colums; j++) {
                double[] clone = hsv_image.get(i, j).clone();
                double hun = clone[0]; // HSV hun
                //判断是否有红色
                if ((hun>=0&&hun<=10)||(hun>=156&&hun<=180)) {
                    if (clone[1] >= 43 && clone[1] <= 255) {
                        if (clone[2] >= 46 && clone[2] <= 255) {
                            OnOrOff="Powered off!";
                            return OnOrOff;
                        }
                    }
                }
                if ((hun>=35&&hun<=77)) {
                    if (clone[1] >= 43 && clone[1] <= 255) {
                        if (clone[2] >= 46 && clone[2] <= 255) {
                            OnOrOff="Powered on!";
                            return OnOrOff;
                        }
                    }
                }
            }
        }
        OnOrOff="Power is not connected!";
        return OnOrOff;
    }

}
