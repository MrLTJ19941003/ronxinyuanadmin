package com.ronxinyuan.common;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 13045 on 2018/4/9.
 */
public class FtpFileUtil {
    //ftp服务器ip地址
    private static final String FTP_ADDRESS = "39.106.22.205";
    //端口号
    private static final int FTP_PORT = 21;
    //用户名
    private static final String FTP_USERNAME = "ftpuser";
    //密码
    private static final String FTP_PASSWORD = "ronxinyuan";
    //图片路径
    private static final String FTP_BASEPATH = "/home/ftpuser/images";

    public  static boolean uploadFile(String[] originFileName,InputStream[] inputStreams){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(FTP_BASEPATH );
            ftp.changeWorkingDirectory(FTP_BASEPATH );
            for(int i=0;i<inputStreams.length;i++){
                InputStream input = inputStreams[i];
                ftp.storeFile(originFileName[i],input);
                input.close();
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
            success=false;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
}
