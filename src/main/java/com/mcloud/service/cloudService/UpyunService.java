package com.mcloud.service.cloudService;

public interface UpyunService {
    void uploadFile(String localFilePath);
    void deleteYunFile(String fileName);
    void downloadFile(String fileName,String saveFilePath);
    void getFileInformation(String fileName);
    void getSpaceCapacity();

}
