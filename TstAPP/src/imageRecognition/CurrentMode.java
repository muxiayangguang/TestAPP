package com.example.imageRecognition;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CurrentMode {
    Temperature aaa=new Temperature();
    String filename="C:\\Users\\18363\\Desktop\\testPic\\mode_model\\";

    String[] ModeCollection=new String[]{"Colding","Heating"};
    List<Mat> ListModeModelMat=new ArrayList<>();
    public String GetMode(Mat src){
        Mat srccc = new Mat();
        Imgproc.threshold(src, srccc, 100, 255, Imgproc.THRESH_BINARY);//二值化
        //模板图List
        for(int j=0;j<ModeCollection.length;j++) {
            Mat modelMatInitialize = Imgcodecs.imread(filename + ModeCollection[j] + ".jpg");
            //Mat modelLaterMat = new Mat();
            //Imgproc.threshold(modelMatInitialize, modelLaterMat, 100, 255, Imgproc.THRESH_BINARY);//二值化
            //Imgcodecs.imwrite("C:\\Users\\18363\\Desktop\\testPic\\2NUM\\"+ModeCollection[j]+".jpg", modelLaterMat);
            ListModeModelMat.add(modelMatInitialize);
        }
        //待比较图List
        List<Mat> ListSrc = new ArrayList<>();
        ListSrc.add(srccc);
        String ModeString = aaa.NumberRecognition(ListSrc,ListModeModelMat);
        String Mode=ModeCollection[Integer.parseInt(ModeString)-18];

        return Mode;
    }
}
