package com.mcloud.service.cloudService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface QiniuServie {
    void uploadFile(String localFilePath);
    void randomAcessUpLoadFile(String localFilePath);
    void urlDownLoadSource(String finalUrl,String fileName,String saveFilePath) throws IOException;
    void downLoadPublicFile(String fileName,String saveFilePath) throws IOException;
    void downLoadPrivateFile(String fileName, String saveFilePath) throws IOException;
    void getYunFileInfomation(String yunFileName);
    void deleteCloudFile(String fileName);
}
