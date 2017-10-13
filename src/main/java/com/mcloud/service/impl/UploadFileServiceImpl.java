package com.mcloud.service.impl;

import com.mcloud.service.upload.deliverFile.PartitionFile;
import com.mcloud.service.upload.fileToMulClouds.MulCloudsDispose;
import org.springframework.stereotype.Service;
import com.mcloud.service.UploadFileService;

/**
 * Created by vellerzheng on 2017/10/13.
 */
@Service
public class UploadFileServiceImpl implements UploadFileService{
    private String path;
    private String pathPart;
    private String filename;
    public String description;
    private int fileSize;




    public void initUploadFile(String path,String pathPart,int fileSize,String description,String filename){
        this.path=path;
        this.pathPart=pathPart;
        this.fileSize=fileSize;
        this.description=description;
        this.filename=filename;
    }


    @Override
    public void dealFileUpload() {


            /* 文件分片*/
        int fs = fileSize/1024/1024/4;     //  unit  MB  , each file after splited
         String srcPath =path+"\\"+filename;
        PartitionFile partitionFile= new PartitionFile();
        boolean spt = partitionFile.split(srcPath,fs,pathPart);

           // 多云上传
        if(spt) {
            MulCloudsDispose mulCloudsDispose = new MulCloudsDispose();
            mulCloudsDispose.getPartFilePath(pathPart);
            mulCloudsDispose.uploadPartFileToClouds();
        }


    }
}
