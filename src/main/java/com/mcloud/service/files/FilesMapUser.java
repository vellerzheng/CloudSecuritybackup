package com.mcloud.service.files;

import com.mcloud.model.FilesEntity;

/**
 * Created by vellerzheng on 2017/10/5.
 */
public class FilesMapUser {
    FilesEntity filesEntity ;
    void filesMapUser(String description,String fileName){
        filesEntity.setDescription(description);
        filesEntity.setFileName(fileName);
    }


    FilesEntity getFilesEntity(){ return  filesEntity;}
}
