package com.mcloud.service;

import java.io.File;

/**
 * Created by vellerzheng on 2017/10/17.
 */
public interface DownloadFileService {

    /**
     * 下载文件服务
     * @param partFilePath    下载的多云分片文件
     * @param fileId           文件名在数据库中的ID
     * @return              返回成功与否
     */
    boolean downloadCloudFilePart(String partFilePath,int fileId);

    /**
     * 得到原始文件
     * @param partFilePath   分片文件路径
     * @param realFilePath     生成真正的原始文件路径
     * @param fileId            文件名在数据库中的id
     * @return              返回真正的文件
     */
    File getRealFile(String partFilePath,String realFilePath,int fileId);
}
