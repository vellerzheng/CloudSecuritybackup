package com.mcloud.service.impl;

import com.mcloud.repository.FileRepository;
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

    private String fileName;
    private int fileId;
    @Autowired
    private FileRepository fileRepository;

    @Override
    public void initManagementFileService(int fileId){
        this.fileId = fileId;
        this.fileName = fileRepository.findOne(fileId).getFileName();
    }

    @Override
    public boolean deleteCloudFile() {
        AliyunOSS aliyun= new AliyunOSS();
        String yunFilePath=fileName+"-0.dat";
        aliyun.deleteFile(yunFilePath);

        Netease netease =new Netease();
        String netsFilePath=fileName+"-1.dat";
        netease.deleteFile(netsFilePath);

        Qcloud qcloud = new Qcloud();
        String dstCosFilePath = fileName+"-2.dat";
        qcloud.deleteFile(dstCosFilePath);

        Qiniu qiniu = new Qiniu();
        String qiniuYunFilePath=fileName+"-3.dat";
        qiniu.deleteCloudFile(qiniuYunFilePath);

        Upyun upyun =new Upyun();
        String upyunFilePath=fileName+"-4.dat";
        upyun.deleteYunFile(upyunFilePath);

        return true;
    }
}
