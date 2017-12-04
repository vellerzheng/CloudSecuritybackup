package com.mcloud.yunData.qcloud;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.*;
import com.qcloud.cos.sign.Credentials;

import java.io.File;

/**
 * Created by vellerzheng on 2017/9/18.
 */
public class Qcloud {
    private static String bucketName = "tencentfile";

    /**
     *
     * @Title: getCOSClient
     * @Description: 生成客户端对象
     * @return
     */
    public  COSClient getCOSClient() {

        // 初始化秘钥信息
        long appId = 1254362959;
        String secretId = "AKIDjLlz2sfh4VyN2ZzgSHoFVaTAf2PBlDdt";
        String secretKey = "DF5jGl5B8ze7VwEMJ5f7QZVKyQfintul";
        // 设置要操作的bucket
        // String bucketName = "goods";
        // 初始化秘钥信息
        Credentials cred = new Credentials(appId, secretId, secretKey);

        // 初始化客户端配置(如设置园区)
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
        clientConfig.setRegion("sh");

        // 生成客户端
        // 初始化cosClient
        COSClient cosClient = new COSClient(clientConfig, cred);

        return cosClient;
   }

    /**
     *
     * @Title: uploadFile
     * @Description:上传文件
     */
    public  String uploadFile(String localFilePath) {
        String fileName = localFilePath.substring((localFilePath.lastIndexOf(File.separator)));
        String yunfilePath = "/backupFile/"+ fileName.replace(File.separator,"");  //key 为上传的文件名

        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,yunfilePath, localFilePath);
        String uploadFileRet = getCOSClient().uploadFile(uploadFileRequest);

        return uploadFileRet;
    }

    /**
     *
     * @Title: downFile
     * @Description: 下载文件
     * @return
     */
    public  String downFile(String yunfileName,String localPathDown) {
        String cosFilePath="/backupFile/"+yunfileName;
        String fileName =cosFilePath.substring((cosFilePath.lastIndexOf("/")));
        String localFilePath = localPathDown+ File.separator+ fileName.replace("/","");  //key 为上传的文件名
        GetFileLocalRequest getFileLocalRequest = new GetFileLocalRequest(bucketName, cosFilePath, localFilePath);
        getFileLocalRequest.setUseCDN(false);
        getFileLocalRequest.setReferer("*.myweb.cn");
        String getFileResult = getCOSClient().getFileLocal(getFileLocalRequest);
        return getFileResult;
    }

    public  String moveFile(String cosFilePath, String dstCosFilePath) {

       // String cosFilePath = "/backupFile/README.txt";
       // String dstCosFilePath = "/README.txt";
        MoveFileRequest moveRequest = new MoveFileRequest(bucketName, cosFilePath, dstCosFilePath);
        String moveFileResult = getCOSClient().moveFile(moveRequest);
        return moveFileResult;
    }

    /**
     *
     * @Title: getFileProp
     * @Description: 获取文件
     * @return
     */
    public  String getFileProp(String yunFilePath) {


        StatFileRequest statFileRequest = new StatFileRequest(bucketName,yunFilePath);
        String statFileRet = getCOSClient().statFile(statFileRequest);

        return statFileRet;
    }

    /**
     *
     * @Title: deleteFile
     * @Description: 删除文件
     * @return
     */
    public String deleteFile(String fileName) {

        String yunFilePath="/backupFile/"+fileName;
        DelFileRequest delFileRequest = new DelFileRequest(bucketName,yunFilePath);
        String delFileRet = getCOSClient().delFile(delFileRequest);

        return delFileRet;
    }



    public static  void main(String[] args) {
        String localFilePath ="D:\\Test\\split\\Hadoop，The Definitive Guide.pdf";
        String saveFilepath = "D:\\Test\\merge";

        String yunFileName="cloudStorageService.pdf-2.dat";
        Qcloud qcloud = new Qcloud();
      // qcloud.uploadFile(localFilePath);
         qcloud.downFile(yunFileName,saveFilepath);

         String cosFilePath = "/backupFile/README.txt";
         String dstCosFilePath = "3662285.pdf-2.dat";
      //   qcloud.moveFile(cosFilePath,dstCosFilePath);
        System.out.println(qcloud.getFileProp(yunFileName));
        System.out.println(qcloud.deleteFile(dstCosFilePath));


    }
}
