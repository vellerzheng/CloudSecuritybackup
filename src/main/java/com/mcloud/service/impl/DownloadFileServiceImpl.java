package com.mcloud.service.impl;

import com.mcloud.repository.FileRepository;
import com.mcloud.service.DownloadFileService;
import com.mcloud.service.download.TransformDownloadFile;
import com.mcloud.yunData.aliyun.AliyunOSS;
import com.mcloud.yunData.netease.Netease;
import com.mcloud.yunData.qcloud.Qcloud;
import com.mcloud.yunData.qiniu.Qiniu;
import com.mcloud.yunData.upyun.Upyun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by vellerzheng on 2017/10/17.
 */
@Service
public class DownloadFileServiceImpl implements DownloadFileService{
    private String fileName;
    private String partFilePath;
    private String realFilePath;
    private int fileId;
    @Autowired
    private FileRepository fileRepository;


    public void initDownloadFileServiceImpl(int fileId,String partFilePath,String realFilePath){
        this.fileId = fileId;
        this.fileName = fileRepository.findOne(fileId).getFileName();
        this.partFilePath = partFilePath;
        this.realFilePath = realFilePath;
    }


    @Override
    public boolean downloadCloudFilePart() {
        AliyunOSS aliyun= new AliyunOSS();
        String yunFilePath=fileName+"-0.dat";
        aliyun.downloadFile(yunFilePath, partFilePath);

        Netease netease =new Netease();
        String netsFilePath=fileName+"-1.dat";
        netease.downFile(netsFilePath,partFilePath);

        Qcloud qcloud = new Qcloud();
        String dstCosFilePath = fileName+"-2.dat";
        qcloud.downFile(dstCosFilePath,partFilePath);

        Qiniu qiniu = new Qiniu();
        String yunFileName=fileName+"-3.dat";
        try {
            qiniu.downLoadPrivateFile(yunFileName,partFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Upyun upyun =new Upyun();
        String upyunFilePath=fileName+"-4.dat";
        String upyunPartFilePath = partFilePath+ File.separator+upyunFilePath;
        upyun.downloadFile(upyunFilePath,upyunPartFilePath);
        return true;
    }

    @Override
    public void getRealFile(){
        TransformDownloadFile transformFile =new TransformDownloadFile();
        transformFile.getPartFilePath(partFilePath);
        transformFile.mergeDownloadFile(realFilePath);
    }




}
