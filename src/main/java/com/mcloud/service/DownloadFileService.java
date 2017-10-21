package com.mcloud.service;

import java.io.File;

/**
 * Created by vellerzheng on 2017/10/17.
 */
public interface DownloadFileService {
    void initDownloadFileServiceImpl(int fileId,String partFilePath,String realFilePath);
    boolean downloadCloudFilePart();
    File getRealFile();
}
