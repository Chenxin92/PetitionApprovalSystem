package com.dfrz.javaprojectstage3.utils;

import java.io.File;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * @author：ChenXin
 * @date 2021/2/22 21:18
 */
public class WordToPdfUtils {
    /**
     * word 转 pdf 方法
     */
    public static int office2PDF(String sourceFile, String destFile) {
        try {
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                return -1;
            }

            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);//端口是固定的，IP根据自己服务所在的服务器进行配置
            connection.connect();

            //获去连接
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);

            //关闭连接
            connection.disconnect();

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        //单个文件转换
        office2PDF("E:\\DownLoad\\picture\\附件1-需求文档.doc", "E:\\DownLoad\\附件1-需求文档.pdf");
        //多个文件转换
        //String path = "多个word文件所在的目录";
        //File f = new File(path);
    }
}
