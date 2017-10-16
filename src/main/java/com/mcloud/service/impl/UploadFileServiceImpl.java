package com.mcloud.service.impl;

import com.mcloud.model.FilesEntity;
import com.mcloud.model.UsersEntity;
import com.mcloud.repository.FileRepository;
import com.mcloud.repository.UserRepository;
import com.mcloud.service.UploadFileService;
import com.mcloud.service.upload.deliverFile.PartitionFile;
import com.mcloud.service.upload.fileToMulClouds.MulCloudsDispose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vellerzheng on 2017/10/13.
 */
@Service
public class UploadFileServiceImpl implements UploadFileService{

    private String path;
    private String pathPart;
    private String filename;
    private String description;
    private int usrId;
    private int fileSize;

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initUploadFile(String path,String pathPart,int fileSize,String description,String filename,int usrEty){
        this.path=path;
        this.pathPart=pathPart;
        this.fileSize=fileSize;
        this.description=description;
        this.filename=filename;
        this.usrId = usrEty;
    }


    @Override
    public void dealFileUpload() {

            /* 文件分片*/
        int fs = fileSize/1024/1024/4;     //  unit  MB  , each file after splited
         String srcPath =path+"\\"+filename;
        PartitionFile partitionFile= new PartitionFile();
        boolean spt = partitionFile.split(srcPath,fs,pathPart);

           // 多云上传
        if(spt) {
            MulCloudsDispose mulCloudsDispose = new MulCloudsDispose();
            mulCloudsDispose.getPartFilePath(pathPart);
            mulCloudsDispose.uploadPartFileToClouds();
        }
    }

    @Override
    public void saveFileInfoToDateBase() {
          List<FilesEntity> allUserFileEty= fileRepository.findByFilesEntityEEndsWith(usrId);
          for(FilesEntity uty:allUserFileEty){
              if(uty.getFileName().equals(filename))
            return;
          }
        FilesEntity fsty=new FilesEntity();
        fsty.setFileName(filename);
        fsty.setDescription(description);
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        String strSize=null;
        if(fileSize<1024)
            strSize = fileSize+"B";
            else if(fileSize<1024*1024)
                strSize = decimalFormat.format((float)fileSize/1024)+"Kb";
            else if(fileSize<1024*1024*1024)
                strSize = decimalFormat.format((float)fileSize/1024/1024)+"M";
            else
                strSize = decimalFormat.format((float)fileSize/1024/1024/1024) +"G";
        fsty.setSize(strSize);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date time=null;
        try{
            time =sdf.parse(sdf.format(new Date()));
        }catch (ParseException e){
            e.printStackTrace();
        }
        fsty.setPubDate(time);
        UsersEntity usEnty= userRepository.findUsersEntityById(usrId);
        fsty.setUserByUserId(usEnty);
        fileRepository.saveAndFlush(fsty);
    }

}
