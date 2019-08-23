package com.example.demoopencv;

import org.opencv.core.Core;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import static org.opencv.core.CvType.CV_8UC1;
import static org.opencv.core.CvType.CV_8UC3;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.rectangle;
import static org.opencv.videoio.Videoio.CAP_PROP_FPS;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_HEIGHT;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_WIDTH;

/**
 * @description 基于opencv Haar分类器的人脸检测
 * @author weiPenghui
 * @date 2018-8-27
 */
public class TestMain {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args){

        String xmlFileName = "D:\\opencv343\\sources\\data\\lbpcascades\\lbpcascade_frontalface_improved.xml";


        CascadeClassifier cascadeClassifier = new CascadeClassifier(xmlFileName);
        if (cascadeClassifier.empty()){
            System.out.println("加载xml模型文件失败！");
        }else{

            VideoCapture capture = new VideoCapture(0);
            if (!capture.isOpened()){
                System.out.println("打开相机失败！");
            }else{

                //设置相机参数
                capture.set(CAP_PROP_FRAME_WIDTH,640);
                capture.set(CAP_PROP_FRAME_HEIGHT,480);
                capture.set(CAP_PROP_FPS,30);

                Mat frame = new Mat(new Size(640,480),CV_8UC3);
                Mat frameGray =  new Mat(new Size(640,480),CV_8UC1);
                MatOfRect objectsRect = new MatOfRect();

                while (true){

                    //读取图像
                    if(!capture.read(frame)){
                        System.out.println("读取相机数据失败！");
                        break;
                    }

                    //转为灰度
                    cvtColor(frame,frameGray,COLOR_BGR2GRAY);

                    //人脸检测
                    cascadeClassifier.detectMultiScale(frameGray,objectsRect);

                    if (!objectsRect.empty()){
                        //绘制矩形
                        Rect[] rects = objectsRect.toArray();
                        for (Rect r:rects){
                            rectangle(frame,r.tl(),r.br(),new Scalar(0,255,255),2);
                        }

                        System.out.println("检测到人脸个数="+rects.length);

                    }

                    //显示
                    HighGui.imshow("facedetect",frame);
                    if (HighGui.waitKey(33)=='q'){
                        break;
                    }

                }

            }


        }

    }

}
