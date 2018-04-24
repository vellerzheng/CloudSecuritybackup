package com.mcloud.service.cloudService;

public interface UpyunService {
    boolean uploadFile(String localFilePath);
    boolean deleteYunFile(String fileName);
    boolean downloadFile(String fileName,String saveFilePath);
    void getFileInformation(String fileName);
    void getSpaceCapacity();

}
