package com.mcloud.service;

/**
 * Created by vellerzheng on 2017/10/13.
 */
public interface UploadFileService {


    String  dealFileUpload(String path,String pathPart,String filename,int fileSize,int usrId);


    void saveFileInfoToDateBase(String pathPart,String filename,String hashFileName,String description,int fileSize,int usrId);
}
