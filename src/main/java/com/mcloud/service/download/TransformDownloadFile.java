package com.mcloud.service.download;

import com.mcloud.service.upload.deliverFile.PartitionFile;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vellerzheng on 2017/10/17.
 */
public class TransformDownloadFile {

    private List<String> listPartFileNames=new ArrayList<>();
    private static Logger logger =Logger.getLogger(TransformDownloadFile.class);


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
            return fileList.length;
        }else {
            logger.info("读取多云下载文件数目出错！");
            return -1;
        }
    }

    public void mergeDownloadFile(String realFilePath){
        if(listPartFileNames.size()==5) {
            PartitionFile partitionFile = new PartitionFile();
            partitionFile.merge(realFilePath, listPartFileNames);
        }else{
            System.out.println("信息不完整，或下载文件数不为5！");
        }


    }

}
