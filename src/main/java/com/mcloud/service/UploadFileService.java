package com.mcloud.service;

/**
 * Created by vellerzheng on 2017/10/13.
 */
public interface UploadFileService {
    void initUploadFile(String path,String pathPart,int fileSize,String describe,String filename);
    void dealFileUpload();
}
