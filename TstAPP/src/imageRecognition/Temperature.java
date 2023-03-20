package com.example.imageRecognition;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Temperature {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static boolean White(double[] array){
        int len=array.length;
        for(int i=0;i<len;i++){
            if(array[i]==0.0) {
                return false;//有黑色像素
            }
        }
        return true;
    }

    public static List<Mat> SplitNumber(Mat src){
        //行
        int src_rows = src.rows();
        // 图像列:宽度width
        int src_colums = src.cols();
        // 图像通道:维度dims/channels
        int img_channels = src.channels();
        double [][] arr=new double[src_colums][src_rows];

        // 图像像素遍历,按通道输出
        for(int i=0;i<img_channels;i++) {
            for (int k = 0; k < src_colums; k++) {
                for (int j = 0; j < src_rows; j++) {
                    arr[k][j] = src.get(j, k)[i];
                }
            }
        }
        int[] a=new int[src_colums];
        int key=0;
        for(int i=0;i<src_colums;i++){
            if(White(arr[i])){
                a[key]=i;//白色竖线坐标
                key++;
            }
        }
        int PictureNum=0;
        List<Mat> MatList=new ArrayList<>();
        for(int i=0;i<key;i++){
            if((a[i+1]-a[i])>1) {
                Rect rect = new Rect(a[i], 0, a[i+1]-a[i], src_rows);
                Mat roi_img = new Mat(src,rect);
                Mat tmp_img = new Mat();
                roi_img.copyTo(tmp_img);
                MatList.add(tmp_img);

                String filename="C:\\Users\\18363\\Desktop\\testPic\\SplitNumber";
                Imgcodecs.imwrite(filename+PictureNum+".jpg", tmp_img);
                PictureNum++;
            }
        }
        return MatList;
    }

    public static String NumberRecognition(List<Mat> src,List<Mat> modelMat){
        String number = "";
        for(int i=0;i<src.size();i++) {
            Mat midPicture=src.get(i);
            int midPicture_rows = midPicture.rows();
            int midPicture_colums = midPicture.cols();
            double similarity=0.0;
            int midJ=0;

            for(int j=0;j<modelMat.size();j++) {

                //Mat modelMatInitialize = Imgcodecs.imread(filename + j + ".jpg", Imgcodecs.IMREAD_GRAYSCALE);
                //Mat modelMat = new Mat();
                //Imgproc.threshold(modelMatInitialize, modelMat, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);//灰度图像二值化

                int modelMat_rows = modelMat.get(j).rows();
                int modelMat_colums = modelMat.get(j).cols();
                int rows=midPicture_rows<modelMat_rows ? midPicture_rows:modelMat_rows;
                int colums=(midPicture_colums<modelMat_colums) ? midPicture_colums:modelMat_colums;

                double count=0;
                for (int m = 0; m < rows; m++) {
                    for (int n = 0; n < colums; n++) {
                        if(midPicture.get(m, n)[0]==modelMat.get(j).get(m, n)[0]){
                            count=count+1.0;
                        }
                    }
                }
                if((count/(rows*colums))>similarity){
                    similarity=count/(rows*colums);
                    midJ=j;
                }
            }
            number=number+String.valueOf(midJ+18);
        }
        return number;
    }

    public static String GetTemperature(Mat src){
        Mat target = new Mat();
        Imgproc.threshold(src, target, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);//灰度图像二值化
        // 保存二值化后图片
        //Imgcodecs.imwrite("C:\\Users\\18363\\Desktop\\testPic\\number_model\\100.jpg", target);
        String filename="C:\\Users\\18363\\Desktop\\testPic\\number_model\\";

        List<Mat> ListMat=new ArrayList<>();
        List<Mat> ListModelMat=new ArrayList<>();
        for(int j=18;j<31;j++) {
            Mat modelMatInitialize = Imgcodecs.imread(filename + j + ".jpg", Imgcodecs.IMREAD_GRAYSCALE);
            Mat modelLaterMat = new Mat();
            Imgproc.threshold(modelMatInitialize, modelLaterMat, 0, 255, Imgproc.THRESH_BINARY);
            ListModelMat.add(modelLaterMat);
        }

        //ListMat=SplitNumber(target);//分开
        ListMat.add(target);
        String number=NumberRecognition(ListMat,ListModelMat);
/*        for(int i=0;i<number.length;i++){
            System.out.print(number[i]);
        }*/
        return number;

    }
}
