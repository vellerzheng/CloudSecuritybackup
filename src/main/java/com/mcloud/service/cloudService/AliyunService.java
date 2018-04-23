package com.mcloud.service.cloudService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AliyunService {
    boolean uploadFile(String localFilePath);
    String uploadMultipartFile(MultipartFile multipartFile, String remotePath) throws IOException;
    void downloadFile(String yunfileName, String saveLocalFilePath);
    void deleteFile( String fileName);
}
