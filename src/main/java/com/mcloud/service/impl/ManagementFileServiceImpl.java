package com.mcloud.service.impl;

import com.mcloud.model.FilesHashEntity;
import com.mcloud.repository.ConfAliyunRespository;
import com.mcloud.repository.HashFileRepository;
import com.mcloud.service.ManagementFileService;
import com.mcloud.service.cloudService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vellerzheng on 2017/10/16.
 */
@Service
public class ManagementFileServiceImpl implements ManagementFileService {


    @Autowired
    private HashFileRepository hashFileRepository;

    @Autowired
    private ConfAliyunRespository confAliyunRespository;
    @Autowired
    AliyunService aliyunService;
    @Autowired
    NeteaseService neteaseService;
    @Autowired
    QcloudService qcloudService;
    @Autowired
    QiniuServie qiniuServie;
    @Autowired
    UpyunService upyunService;

    @Override
    public boolean deleteCloudFile(int hashFileId) {
        FilesHashEntity filesHashEntity =hashFileRepository.findOne(hashFileId);


        String yunFilePath=filesHashEntity.getAliyunHash();
        if(yunFilePath != null)
            aliyunService.deleteFile(yunFilePath);


        String netsFilePath=filesHashEntity.getNeteaseHash();
        if(netsFilePath != null)
            neteaseService.deleteFile(netsFilePath);

        String dstCosFilePath =filesHashEntity.getQcloudHash();
        if(dstCosFilePath != null)
            qcloudService.deleteFile(dstCosFilePath);


        String qiniuYunFilePath=filesHashEntity.getQiniuHash();
        if(qiniuYunFilePath != null)
            qiniuServie.deleteCloudFile(qiniuYunFilePath);


        String upyunFilePath=filesHashEntity.getUpyunHash();
        if(upyunFilePath != null)
            upyunService.deleteYunFile(upyunFilePath);

        return true;
    }
}
