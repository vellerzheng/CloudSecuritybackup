package com.mcloud.service.impl;

import com.mcloud.model.FilesHashEntity;
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


    @Autowired
    private HashFileRepository hashFileRepository;


    @Override
    public boolean deleteCloudFile(int hashFileId) {
        FilesHashEntity filesHashEntity =hashFileRepository.findOne(hashFileId);

        AliyunOSS aliyun= new AliyunOSS();
        String yunFilePath=filesHashEntity.getAliyunHash();
        if(yunFilePath != null)
            aliyun.deleteFile(yunFilePath);

        Netease netease =new Netease();
        String netsFilePath=filesHashEntity.getNeteaseHash();
        if(netsFilePath != null)
            netease.deleteFile(netsFilePath);

        Qcloud qcloud = new Qcloud();
        String dstCosFilePath =filesHashEntity.getQcloudHash();
        if(dstCosFilePath != null)
            qcloud.deleteFile(dstCosFilePath);

        Qiniu qiniu = new Qiniu();
        String qiniuYunFilePath=filesHashEntity.getQiniuHash();
        if(qiniuYunFilePath != null)
            qiniu.deleteCloudFile(qiniuYunFilePath);

        Upyun upyun =new Upyun();
        String upyunFilePath=filesHashEntity.getUpyunHash();
        if(upyunFilePath != null)
            upyun.deleteYunFile(upyunFilePath);

        return true;
    }
}
