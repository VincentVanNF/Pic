package com.example.Service;


import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

@Component(value = "Download")
public class DownLoadFromURL {

    public static File getFile(String url,String name) throws Exception {
        //对本地文件命名
//        String fileName = "src\\main\\webapp\\WEB-INF\\Pics\\Back.jpg";
        String fileName = name;
        File file = null;

        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = new File(fileName);

            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }

//    public static void main(String[] args) throws Exception {
//
//
////        InputStream inStream = new FileInputStream(getFile("https://static01.versa-ai.com/images/demo/c02.jpg")); // 创建文件输入流，指向路径可以是：FilePath1，也可以是file1
////        BufferedInputStream bInStream = new BufferedInputStream(inStream);
////
////        OutputStream outStream = new FileOutputStream(new File("FilePath2"));
////        BufferedOutputStream bOutStream = new BufferedOutputStream(outStream);
////
////        int b;
////        while((b = bInStream.read()) != -1){
////            bOutStream.write(b);
////        }
////
////        System.out.println("文件拷贝完成！");
////        bInStream.close();
////        bOutStream.close();
//    }
}
