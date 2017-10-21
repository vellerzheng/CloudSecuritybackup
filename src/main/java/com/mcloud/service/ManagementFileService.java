package com.mcloud.service;

/**
 * Created by vellerzheng on 2017/10/16.
 */
public interface ManagementFileService {
    boolean deleteCloudFile();
    void initManagementFileService(int fileId,int hashFileId);
}
