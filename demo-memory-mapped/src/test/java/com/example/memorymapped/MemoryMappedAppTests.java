package com.example.memorymapped;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemoryMappedAppTests {

    /***
     * 拷贝速度最快，但是视频文件不能播放  毫秒级别
     * **/
    @Test
    public void contextLoads() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("d:/追龙2.mp4"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("e:/追龙2.mp4"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        //byte[] dst = new byte[Integer.MAX_VALUE];
        //inMappedBuf.get(dst);
        //outMappedBuf.put(inMappedBuf.get());
        byte[] dst = new byte[1024];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inMappedBuf.force();
        outMappedBuf.force();
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        log.info("拷贝文件消耗时间{}",(end-start)/1000);
    }

    /**
     * 拷贝视频文件可以播放，但是消耗时间太长
     * **/
    @Test
    public void test3() throws IOException{
        long start = System.currentTimeMillis();
        // 封装数据源
        FileInputStream fis = new FileInputStream("E:\\电影\\雍正王朝\\雍正王朝.01.第一集.v3.[R5_DVDRIP][x264-Q23.AAC][853A1097].mkv");
        // 封装目的地
        FileOutputStream fos = new FileOutputStream("e:\\t.mp4");

        // 复制数据
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }
        // 释放资源
        fos.close();
        fis.close();
        log.info(" 消耗时间:{} 秒",(System.currentTimeMillis()-start)/1000);
    }

       /**
     * 支持大视频拷贝 1.28G 28秒
     * **/
    @Test
    public void test4() throws IOException{
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("d:\\追龙2.mp4");
        FileChannel in = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("e:\\t.mp4");
        FileChannel out = fos.getChannel();
        out.transferFrom(in,0,in.size());
        fis.close();
        fos.close();
        in.close();
        out.close();
        log.info(" 消耗时间:{} 秒",(System.currentTimeMillis()-start)/1000);
    }
}
