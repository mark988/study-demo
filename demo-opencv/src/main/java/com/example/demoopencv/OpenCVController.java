package com.example.demoopencv;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/***
 * @author : 马晓光
 * @date   : 2019/8/23
 * @description :
 **/
@Slf4j
@RestController
public class OpenCVController {
    @Value("classpath:haarcascade_frontalface_alt.xml")
    private Resource xml;

    @RequestMapping(value = "/face",method = RequestMethod.POST)
    public void FaceDetector(HttpServletResponse response, MultipartFile file) throws IOException {
        log.info(" file :{}",file);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        log.info("人脸检测开始……");

        // 创建临时文件，因为boot打包后无法读取文件内的内容
        File targetXmlFile = new File("src/" + xml.getFilename() + "");
        FileUtils.copyInputStreamToFile(xml.getInputStream(), targetXmlFile);
        CascadeClassifier faceDetector = new CascadeClassifier(targetXmlFile.toString());
        if (faceDetector.empty()) {
            log.info("请引入xml文件……");
            return;
        }
        // 创建图片tempFile
        File tempFile = new File("e:/" + file.getOriginalFilename() + "");
        FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);

        // 读取创建的图片tempFile
        Mat image = Imgcodecs.imread(tempFile.toString());
        MatOfRect faceDetections = new MatOfRect();
        // 进行人脸检测
        faceDetector.detectMultiScale(image, faceDetections);
        log.info(String.format("检测到人脸： %s", faceDetections.toArray().length));
        Integer i = 1;
        // 制图将图填充到image中
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image,
                              new Point(rect.x, rect.y),
                              new Point(rect.x + rect.width, rect.y + rect.height),
                              new Scalar(0, 255, 0),
                     3);
            // 进行图片裁剪
            imageCut(tempFile.toString(), i + ".jpg", rect.x, rect.y, rect.width, rect.height);
            i++;
        }
        // 下面部分是返回给页面
        String filename = file.getOriginalFilename();
        Imgcodecs.imwrite(filename, image);
        File imgFile = new File(filename);
        if (imgFile.exists()) {
            response.getOutputStream().write(toByteArray(imgFile));
            response.getOutputStream().close();
        }

        // 删除临时文件
        if (targetXmlFile.exists() && targetXmlFile.isFile()) {
            if (targetXmlFile.delete()) {
                log.info("删除临时文件" + targetXmlFile + "成功！");
            }
        }
        if (imgFile.exists() && imgFile.isFile()) {
            if (imgFile.delete()) {
                log.info("删除临时文件" + imgFile + "成功！");
            }
        }
        if (tempFile.exists() && tempFile.isFile()) {
            if (tempFile.delete()) {
                log.info("删除临时文件" + tempFile + "成功！");
            }
        }
    }

    public static void imageCut(String imagePath, String outFile, int posX, int posY, int width, int height) {
        /**
         * 原始图像
         * **/
        Mat image = Imgcodecs.imread(imagePath);
        /**
         *   截取的区域：参数,坐标X,坐标Y,截图宽度,截图长度
         * **/
        Rect rect = new Rect(posX, posY, width, height);
        /**
         * 两句效果一样
         * **/
        Mat sub = image.submat(rect);
        Mat mat = new Mat();
        Size size = new Size(width, height);
        Imgproc.resize(sub, mat, size);// 将人脸进行截图并保存

        Imgcodecs.imwrite(outFile, mat);
        log.info(String.format("图片裁切成功，裁切后图片文件为： %s", outFile));
    }

    public static byte[] toByteArray(File file) throws IOException {
        File f = file;
        if (!f.exists()) {
            throw new FileNotFoundException("file not exists");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

}
