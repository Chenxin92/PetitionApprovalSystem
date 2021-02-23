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
     * DOC转PDF
     *
     * @param sourceFile 源文件路径
     * @param destFile 生成文件路径
     * @return
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

            // 端口是固定的，IP根据自己服务所在的服务器进行配置
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();

            // 获取连接
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);

            // 关闭连接
            connection.disconnect();

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        //单个文件转换
        office2PDF("/E:/Course/Full-time/Third/javaprojectstage3/target/classes//static/upload/petition/doc/\\附件1-需求文档.doc", "/E:/Course/Full-time/Third/javaprojectstage3/target/classes//static/upload/petition/doc/\\附件1-需求文档.PDF");
        //多个文件转换
        //String path = "多个word文件所在的目录";
        //File f = new File(path);
    }
}
