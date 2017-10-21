package com.mcloud.service.impl;

import com.mcloud.model.FilesHashEntity;
import com.mcloud.repository.FileRepository;
import com.mcloud.repository.HashFileRepository;
import com.mcloud.service.ManagementFileService;
import com.mcloud.yunData.aliyun.AliyunOSS;
import com.mcloud.yunData.netease.Netease;
import com.mcloud.yunData.qcloud.Qcloud;
import com.mcloud.yunData.qiniu.Qiniu;
import com.mcloud.yunData.upyun.Upyun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vellerzheng on 2017/10/16.
 */
@Service
public class ManagementFileServiceImpl implements ManagementFileService {

  //  private String fileName;
    private int fileId;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private HashFileRepository hashFileRepository;
    private FilesHashEntity filesHashEntity;

    /**
     *  初始化
     * @param fileId  为原文件的file_id
     * @param hashFileId 为file_hash 表中的id;
     */
    @Override
    public void initManagementFileService(int fileId,int hashFileId){
        this.fileId = fileId;
      //  this.fileName = fileRepository.findOne(fileId).getFileName();
        this.filesHashEntity = hashFileRepository.findOne(hashFileId);
    }

    @Override
    public boolean deleteCloudFile() {
        AliyunOSS aliyun= new AliyunOSS();
        String yunFilePath=filesHashEntity.getAliyunHash();
        aliyun.deleteFile(yunFilePath);

        Netease netease =new Netease();
        String netsFilePath=filesHashEntity.getNeteaseHash();
        netease.deleteFile(netsFilePath);

        Qcloud qcloud = new Qcloud();
        String dstCosFilePath =filesHashEntity.getQcloudHash();
        qcloud.deleteFile(dstCosFilePath);

        Qiniu qiniu = new Qiniu();
        String qiniuYunFilePath=filesHashEntity.getQiniuHash();
        qiniu.deleteCloudFile(qiniuYunFilePath);

        Upyun upyun =new Upyun();
        String upyunFilePath=filesHashEntity.getUpyunHash();
        upyun.deleteYunFile(upyunFilePath);

        return true;
    }
}
