package com.mcloud.service.download;

import com.mcloud.service.upload.deliverFile.PartitionFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vellerzheng on 2017/10/17.
 */
public class TransformDownloadFile {

    private List<String> listPartFileNames=new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(TransformDownloadFile.class);


    public int getPartFilePath(String partFileDirectory){
        File file =new File(partFileDirectory);
        File[] fileList=file.listFiles();
      //  vectPartFileNames = new String[5];

        if(fileList!=null) {
            for (File aFileList : fileList) {
                if (aFileList.isFile()) {
                    listPartFileNames.add(aFileList.getPath());
                }
            }
        }else {
            logger.error("读取多云下载文件数目出错！");
            return -1;
        }
        listPartFileNames.sort(String::compareTo);
        return fileList.length;
    }

    public String mergeDownloadFile(String realFilePath){
        String mergedFilePath=null;
        if(listPartFileNames.size()==5) {
            PartitionFile partitionFile = new PartitionFile();
            mergedFilePath =partitionFile.merge(listPartFileNames,realFilePath);
        }else{
            logger.error("信息不完整，或下载文件数不为5！");
        }

        return mergedFilePath;
    }

}
