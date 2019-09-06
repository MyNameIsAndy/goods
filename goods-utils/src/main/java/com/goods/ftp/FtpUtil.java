package com.goods.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Ftp工具类
 * 需要导入commons-net-3.4.jar这个包
 */


@Slf4j
@Component
public class FtpUtil {

    private FTPClient ftpClient=null;

    /**
     * 获取ftp连接
     * @param ftpBean ftp连接属性
     * @return
     * @throws Exception
     */
    public boolean connect(FtpBean ftpBean) throws Exception{
        ftpClient=new FTPClient();
        boolean flag=false;
        int reply;
        try{
            if (ftpBean.getPort()==null) {
                ftpClient.connect(ftpBean.getIpAddr(),21);
            }else{
                ftpClient.connect(ftpBean.getIpAddr(),ftpBean.getPort());
            }
            //ftp登陆
            ftpClient.login(ftpBean.getUserName(), ftpBean.getPwd());
            //设置文件传输类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //检查延时
            reply = ftpClient.getReplyCode();
            //如果延时不在200到300之间，就关闭连接
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return flag;
            }
            ftpClient.setControlEncoding(ftpBean.getEncoding());
            flag = true;
            return flag;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 关闭ftp连接
     */
    public void closeFtp(){
        if (ftpClient!=null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 下载文件，调用前需要创建连接，调用后需要关闭连接
     * @param filename 本地文件名
     * @param localBaseDir 本地目录
     * @param remoteBaseDir 远程目录
     * @return
     * @throws Exception
     */
    public boolean down(String filename,String localBaseDir,String remoteBaseDir ) throws Exception{
        try {
            FTPFile[] files = null;
            boolean changedir = ftpClient.changeWorkingDirectory(remoteBaseDir);
            if (changedir) {
                files=ftpClient.listFiles();
                for(FTPFile tempFTPFile :files){
                    if(tempFTPFile.getName().equals(filename)){
                        FileOutputStream outputStream = new FileOutputStream(localBaseDir+ filename);
                        ftpClient.retrieveFile(filename, outputStream);
                        outputStream.flush();
                        outputStream.close();
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("下载过程中出现异常");
        }
        return false;
    }

    /**
     * 上传文件，调用前需要创建连接，调用后需要关闭连接
     * @param filename 本地文件名
     * @param localBaseDir 本地目录
     * @param remoteBaseDir 远程目录
     * @return
     * @throws Exception
     */
    public boolean upload(String filename,String localBaseDir,String remoteBaseDir ) throws Exception{
        boolean returnIs=false;
        try {
            FTPFile[] files = null;
            boolean changedir = ftpClient.changeWorkingDirectory(remoteBaseDir);
            if (changedir) {
                FileInputStream fileInput = new FileInputStream(localBaseDir+File.separator+filename);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //上传文件
                returnIs=ftpClient.storeFile(filename, fileInput);
                fileInput.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("上传过程中出现异常");
        }
        return returnIs;
    }

    /**
     * 下载文件，自动创建和关闭连接
     * @param ftpBean   ftp连接属性
     * @param filename 本地文件名
     * @param localBaseDir 本地目录
     * @param remoteBaseDir 远程目录
     * @return
     * @throws Exception
     */
    public boolean down(FtpBean ftpBean,String filename,String localBaseDir,String remoteBaseDir ) throws Exception{
        if (connect(ftpBean)) {
            File localFilePath =  new File(localBaseDir);
            if(!localFilePath.exists()){//自动创建本地目录
                localFilePath.mkdirs();
            }
            return down(filename, localBaseDir, remoteBaseDir );
        }else{
            log.error("链接失败！");
        }
        return false;
    }

    /**
     * 上传文件，自动创建和关闭连接
     * @param ftpBean   ftp连接属性
     * @param filename 本地文件名
     * @param localBaseDir 本地目录
     * @param remoteBaseDir 远程目录
     * @return
     * @throws Exception
     */
    public boolean upload(FtpBean ftpBean,String filename,String localBaseDir,String remoteBaseDir ) throws Exception{
        if (connect(ftpBean)) {
            return upload(filename, localBaseDir, remoteBaseDir );
        }else{
            log.error("链接失败！");
        }
        return false;
    }
    public static void main(String[] args){
        System.out.println("================aaa========1==");
        FtpBean ftpBean=new FtpBean();

        String testHost="10.7.101.211";
        String testUserName="PAY";
        String testPassword="0";
        int ftpPort=21;
        ftpBean.setIpAddr(testHost);
        ftpBean.setPort(ftpPort);
        ftpBean.setUserName(testUserName);
        ftpBean.setPwd(testPassword);
        FtpUtil ftpUtil=new FtpUtil();

        String remoteBaseDir="/TMP";
        String localBaseDir="D:\\";
        String filename="test1.txt";
        try{
           ftpUtil.upload(ftpBean,filename,localBaseDir,remoteBaseDir);
           //ftpUtil.down(ftpBean,filename,localBaseDir,remoteBaseDir);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("================aaa=======2===");
    }
}  