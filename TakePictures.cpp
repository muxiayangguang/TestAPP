#include "pch.h"
#include <opencv2/opencv.hpp>
#include<iostream>
#define interval 100  //修改这里以改变多久保存一帧

using namespace cv;
using namespace std;

VideoCapture Cam1, Cam2;
const int  weigth = 1280;
const int height = 480;
static string Processed = "C:\\Users\\18363\\Desktop\\testPic\\ProcessedImage\\ProcessedImage0.jpg", a = "0";
static int seat = 0;
static Mat input_image;
static int i = 0;
void Save(int &imgnum, int amount)
{
	if (imgnum < amount)
	{
		a = to_string(imgnum);
		seat = floor((imgnum - 1) / 10);
		//Left = Left.replace(4 + seat, 1, a);
		//Right = Right.replace(5 + seat, 1, a);
		imwrite(Processed, input_image);
		//imwrite(Right, rightImage);
		//imgnum += 1;
	}
}



void SetCam(int weigth, int height, int num)
{
	string a = "0";
	string Error;
	VideoCapture Cam(0);
	/*设定缓冲区大小*/
	Cam.set(CV_CAP_PROP_FRAME_WIDTH, weigth);
	Cam.set(CV_CAP_PROP_FRAME_HEIGHT, height);

	while (!Cam.isOpened())
	{

		a = to_string(num);
		Error = "cannot open the camera1!";
		Error = Error.replace(22, 1, a);
		//Error.copy(error, 24, 0);//这里5代表复制几个字符，0代表复制的位置，
	}

	//namedWindow("摄像头");//关键一句代码
	while (true) {
		Cam >> input_image;//将影像传入图片
		imshow("Image", input_image);
		Save(i, 20);
		if (27 == waitKey(10000))

			break;
	}
	return;
}

void ReadVideo() {
	VideoCapture capture("C:/Users/18363/Desktop/test.mp4");
	cout << "111";
	if (!capture.isOpened()) {
		return;
	}
	int imgIndex = 0;

	Mat frame;
	capture >> frame;
	while (!frame.empty()) {

		string output = "C:/Users/18363/Desktop/testPic/frame/" + to_string(imgIndex/100) + ".jpg";

		if (imgIndex % interval == 0) {
			//第一个参数是写入的文件名，第二个参数是Mat类型的图像数据。
			imwrite(output, frame);
		}

		imgIndex++;
		capture >> frame;
	}
	return;
}


int main()
{
	//SetCam(weigth, height, 10);
	while (true) {
		ReadVideo();
	}
	
	return 0;
}