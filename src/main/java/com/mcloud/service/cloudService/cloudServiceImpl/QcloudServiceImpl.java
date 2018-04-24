package com.mcloud.service.cloudService.cloudServiceImpl;

import com.mcloud.model.ConfQcloudEntity;
import com.mcloud.repository.ConfQcloudRespository;
import com.mcloud.service.cloudService.QcloudService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.*;
import com.qcloud.cos.sign.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by vellerzheng on 2017/9/18.
 */
@Service
public class QcloudServiceImpl implements QcloudService{

    @Autowired
    private ConfQcloudRespository confQcloudRespository;
    private ConfQcloudEntity confQcloudEntity;

    private COSClient cosClient;
    /**
     *
     * @Title: getCOSClient
     * @Description: 生成客户端对象
     * @return
     */
    public  void initCOSClient() {

        // 初始化秘钥信息
 /*       long appId = 1254362959;
        String secretId = "AKIDjLlz2sfh4VyN2ZzgSHoFVaTAf2PBlDdt";
        String secretKey = "DF5jGl5B8ze7VwEMJ5f7QZVKyQfintul";*/
        if(confQcloudEntity == null)
          confQcloudEntity = confQcloudRespository.findOne(1);

        if(cosClient  == null) {
            // 初始化秘钥信息
            Credentials cred = new Credentials(Long.parseLong(confQcloudEntity.getAppId()), confQcloudEntity.getSecretId(), confQcloudEntity.getSecretKey());

            // 初始化客户端配置(如设置园区)
            // 初始化客户端配置
            ClientConfig clientConfig = new ClientConfig();
            // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
            clientConfig.setRegion("sh");

            // 生成客户端
            // 初始化cosClient
            cosClient = new COSClient(clientConfig, cred);
        }


   }

    /**
     *
     * @Title: uploadFile
     * @Description:上传文件
     */
    public  String uploadFile(String localFilePath) {
        initCOSClient();
        String fileName = localFilePath.substring((localFilePath.lastIndexOf(File.separator)));
        String yunfilePath = "/backupFile/"+ fileName.replace(File.separator,"");  //key 为上传的文件名
        UploadFileRequest uploadFileRequest = new UploadFileRequest(confQcloudEntity.getBucketName(),yunfilePath, localFilePath);
        String uploadFileRet = cosClient.uploadFile(uploadFileRequest);

        return uploadFileRet;
    }

    /**
     *
     * @Title: downFile
     * @Description: 下载文件
     * @return
     */
    public  String downFile(String yunfileName,String localPathDown) {
        initCOSClient();
        String cosFilePath="/backupFile/"+yunfileName;
        String fileName =cosFilePath.substring((cosFilePath.lastIndexOf("/")));
        String localFilePath = localPathDown+ File.separator+ fileName.replace("/","");  //key 为上传的文件名
        GetFileLocalRequest getFileLocalRequest = new GetFileLocalRequest(confQcloudEntity.getBucketName(), cosFilePath, localFilePath);
        getFileLocalRequest.setUseCDN(false);
        getFileLocalRequest.setReferer("*.myweb.cn");
        String getFileResult = cosClient.getFileLocal(getFileLocalRequest);
        return getFileResult;
    }

    public  String moveFile(String cosFilePath, String dstCosFilePath) {

        initCOSClient();
        MoveFileRequest moveRequest = new MoveFileRequest(confQcloudEntity.getBucketName(), cosFilePath, dstCosFilePath);
        String moveFileResult = cosClient.moveFile(moveRequest);
        return moveFileResult;
    }

    /**
     *
     * @Title: getFileProp
     * @Description: 获取文件
     * @return
     */
    public  String getFileProp(String yunFilePath) {

        initCOSClient();
        StatFileRequest statFileRequest = new StatFileRequest(confQcloudEntity.getBucketName(),yunFilePath);
        String statFileRet = cosClient.statFile(statFileRequest);

        return statFileRet;
    }

    /**
     *
     * @Title: deleteFile
     * @Description: 删除文件
     * @return
     */
    public String deleteFile(String fileName) {
        initCOSClient();
        String yunFilePath="/backupFile/"+fileName;
        DelFileRequest delFileRequest = new DelFileRequest(confQcloudEntity.getBucketName(),yunFilePath);
        String delFileRet = cosClient.delFile(delFileRequest);

        return delFileRet;
    }



/*    public static  void main(String[] args) {
        String localFilePath ="D:\\Test\\split\\Hadoop，The Definitive Guide.pdf";
        String saveFilepath = "D:\\Test\\merge";

        String yunFileName="cloudStorageService.pdf-2.dat";
        QcloudServiceImpl qcloud = new QcloudServiceImpl();
      // qcloud.uploadFile(localFilePath);
         qcloud.downFile(yunFileName,saveFilepath);

         String cosFilePath = "/backupFile/README.txt";
         String dstCosFilePath = "3662285.pdf-2.dat";
      //   qcloud.moveFile(cosFilePath,dstCosFilePath);
        System.out.println(qcloud.getFileProp(yunFileName));
        System.out.println(qcloud.deleteFile(dstCosFilePath));


    }*/
}
