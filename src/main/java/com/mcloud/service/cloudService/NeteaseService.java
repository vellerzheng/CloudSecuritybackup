package com.mcloud.service.cloudService;



public interface NeteaseService {


    boolean uploadMultiPartFile(String localFilePath);
    boolean uploadFile(String localFilePath);
    boolean downFile(String yunFilePath,String localPathDown);
    boolean deleteFile(String yunFilePath);
}
