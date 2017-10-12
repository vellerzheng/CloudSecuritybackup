package src.service.files;

import src.model.FilesEntity;

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
