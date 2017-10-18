package com.mcloud.service;

/**
 * Created by vellerzheng on 2017/10/17.
 */
public interface DownloadFileService {
    void initDownloadFileServiceImpl(int fileId,String partFilePath,String realFilePath);
    boolean downloadCloudFilePart();
    void getRealFile();
}
