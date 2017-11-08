package com.mcloud.service.impl;

import com.mcloud.model.FilesEntity;
import com.mcloud.model.FilesHashEntity;
import com.mcloud.repository.FileRepository;
import com.mcloud.repository.HashFileRepository;
import com.mcloud.service.DownloadFileService;
import com.mcloud.service.download.TransformDownloadFile;
import com.mcloud.service.supportToolClass.FileManage;
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
    private FilesEntity filesEntity;
    @Autowired
    private HashFileRepository hashFileRepository;
    private FilesHashEntity filesHashEntity;



    public void initDownloadFileServiceImpl(int fileId,String partFilePath,String realFilePath){
        this.fileId = fileId;
        this.filesHashEntity= hashFileRepository.findEntityByFileId(fileId);
        this.filesEntity = fileRepository.findOne(fileId);
        this.partFilePath = partFilePath;
        this.realFilePath = realFilePath;
    }


    @Override
    public boolean downloadCloudFilePart() {
        AliyunOSS aliyun= new AliyunOSS();
        String yunFilePath=filesHashEntity.getAliyunHash();
        if(yunFilePath!=null)
            aliyun.downloadFile(yunFilePath, partFilePath);


        Netease netease =new Netease();
        String netsFilePath=filesHashEntity.getNeteaseHash();
        if(netsFilePath!=null)
            netease.downFile(netsFilePath,partFilePath);

        Qcloud qcloud = new Qcloud();
        String dstCosFilePath = filesHashEntity.getQcloudHash();
        if(dstCosFilePath!=null)
            qcloud.downFile(dstCosFilePath,partFilePath);

        Qiniu qiniu = new Qiniu();
        String yunFileName=filesHashEntity.getQiniuHash();
        try {
            if(yunFileName!=null)
                qiniu.downLoadPrivateFile(yunFileName,partFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Upyun upyun =new Upyun();
        String upyunFilePath=filesHashEntity.getUpyunHash();
        if(upyunFilePath!=null) {
            String upyunPartFilePath = partFilePath + File.separator + upyunFilePath;
            upyun.downloadFile(upyunFilePath, upyunPartFilePath);
        }
        return true;
    }

    @Override
    public File getRealFile(){
        String filePath=null;
        TransformDownloadFile transformFile =new TransformDownloadFile();
        int numfile = transformFile.getPartFilePath(partFilePath);
        if(numfile==5) {
            transformFile.mergeDownloadFile(realFilePath);
                  /*需要修改上传文件命名*/
            filePath = realFilePath +File.separator+filesHashEntity.getFileHash();
        }
        if(numfile==1){
            filePath =partFilePath+File.separator+filesHashEntity.getFileHash();
        }

        return FileManage.md5FileNameToRealFilename(filePath,filesEntity.getFileName());
    }




}
