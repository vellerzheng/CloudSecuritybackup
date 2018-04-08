package com.mcloud.service.impl;

import com.mcloud.model.FilesEntity;
import com.mcloud.model.FilesHashEntity;
import com.mcloud.repository.FileRepository;
import com.mcloud.repository.HashFileRepository;
import com.mcloud.service.DownloadFileService;
import com.mcloud.service.download.TransformDownloadFile;
import com.mcloud.util.common.FileManage;
import com.mcloud.service.supportToolClass.fileHandle.FileEncAndDecByDES;
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


    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private HashFileRepository hashFileRepository;


    @Override
    public boolean downloadCloudFilePart(String partFilePath,int fileId) {

        FilesHashEntity filesHashEntity;
         filesHashEntity = hashFileRepository.findEntityByFileId(fileId);
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
    public File getRealFile(String partFilePath,String realFilePath,int fileId){

        FilesHashEntity filesHashEntity;
        filesHashEntity = hashFileRepository.findEntityByFileId(fileId);

        FilesEntity filesEntity;
        filesEntity = fileRepository.findOne(fileId);
        String filePath=null;


        TransformDownloadFile transformFile =new TransformDownloadFile();
        int numFile = transformFile.getPartFilePath(partFilePath);
        if(numFile==5) {
            String mergedFile = realFilePath+File.separator+"mergedfile";
            File mergedFilePart = new File(mergedFile);
            //判断路径是否存在，如果不存在就创建一个
            if (!mergedFilePart.exists()) {
                mergedFilePart.mkdirs();
            }
            //合并文件
            String mergedFilePath = transformFile.mergeDownloadFile(mergedFile);

            //解密
            FileEncAndDecByDES bt = new FileEncAndDecByDES(filesEntity.getUserByUserId().getUsername());
                              /*需要修改上传文件命名*/
            filePath = realFilePath +File.separator+new File(mergedFilePath).getName();
            try {
                bt.decrypt(mergedFilePath,filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }

           /*判断加密的文件路径是否存在，如果存在就删除*/
            if (mergedFilePart.exists()) {
                FileManage.deleteDirectory(mergedFile);
            }

        }
        if(numFile==1){
            //解密
            FileEncAndDecByDES bt = new FileEncAndDecByDES(filesEntity.getUserByUserId().getUsername());
                              /*需要修改上传文件命名*/
            String downloadPartFileName = FileManage.getPartFileName(partFilePath).get(0);
            String downloadPartFileNamePath =partFilePath+File.separator+downloadPartFileName;
            filePath = realFilePath +File.separator+downloadPartFileName;
            try {
                bt.decrypt(downloadPartFileNamePath,filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return FileManage.md5FileNameToRealFilename(filePath,filesEntity.getFileName());
    }


}
