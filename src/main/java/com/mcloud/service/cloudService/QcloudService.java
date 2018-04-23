package com.mcloud.service.cloudService;



public interface QcloudService {

    String uploadFile(String localFilePath);
    String downFile(String yunfileName,String localPathDown);
    String moveFile(String cosFilePath, String dstCosFilePath);
    String getFileProp(String yunFilePath);
    String deleteFile(String fileName);
}
