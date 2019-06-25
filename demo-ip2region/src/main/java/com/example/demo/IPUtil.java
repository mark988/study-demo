package com.example.demo;

/***
 * @author : 马晓光
 * @date   : 2019/6/25
 * @description :
 **/
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.ClassPathResource;

public class IPUtil {
    public static String getCityInfo(String ip){
        //db
       // String dbPath = IPUtil.class.getResource("/ip2region.db").getPath();
        InputStream inputStream =  null;
        //为什么这么使用？因为调试的时候可以直接读取resource目录下的文件，但是打jar后
        //这个文件就读取不到了。所以采用流的方式读取。
        ClassPathResource resource = new ClassPathResource("ip2region.db");
        try {
             inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file= new File("src/main/resources/targetFile.tmp");
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File("src/main/resources/targetFile.tmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }



        if ( file.exists() == false ) {
            System.out.println("Error: Invalid ip2region.db file");
        }

        //查询算法
       // int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        //DbSearcher.BINARY_ALGORITHM //Binary
        //int algorithm = DbSearcher.MEMORY_ALGORITYM //Memory
        int algorithm = DbSearcher.MEMORY_ALGORITYM ;
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, "src/main/resources/targetFile.tmp");

            Method method = null;
            switch ( algorithm )
            {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
                default:
                    break;
            }

            DataBlock dataBlock = null;
            if ( Util.isIpAddress(ip) == false ) {
                System.out.println("Error: Invalid ip address");
            }

            dataBlock  = (DataBlock) method.invoke(searcher, ip);

            return dataBlock.getRegion();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}