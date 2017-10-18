package com.mcloud.service.download;

import com.mcloud.service.upload.deliverFile.PartitionFile;

import java.io.File;

/**
 * Created by vellerzheng on 2017/10/17.
 */
public class TransformDownloadFile {

    private String[] vectPartFileNames;

    public void getPartFilePath(String partFileDirectory){
        File file =new File(partFileDirectory);
        File[] fileList=file.listFiles();
        vectPartFileNames = new String[5];

        for(int i = 0; fileList.length > i; i++){
            if(fileList[i].isFile()){
                vectPartFileNames[i]=fileList[i].getPath();
            }
        }
    }

    public void mergeDownloadFile(String realFilePath){
        if(vectPartFileNames.length==5) {
            PartitionFile partitionFile = new PartitionFile();
            partitionFile.merge(realFilePath, vectPartFileNames);
        }else{
            System.out.println("信息不完整，或下载文件数不为5！");
        }


    }

}
