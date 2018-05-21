package com.mcloud.service.impl;

import com.mcloud.model.FilesEntity;
import com.mcloud.model.FilesHashEntity;
import com.mcloud.model.UsersEntity;
import com.mcloud.repository.FileRepository;
import com.mcloud.repository.HashFileRepository;
import com.mcloud.repository.UserRepository;
import com.mcloud.service.UploadFileService;
import com.mcloud.service.cloudService.QcloudService;
import com.mcloud.service.upload.MulCloudSDisposeService;
import com.mcloud.util.common.CustomDateConverter;
import com.mcloud.util.common.FileManage;
import com.mcloud.service.supportToolClass.fileHandle.FileEncAndDecByDES;
import com.mcloud.service.upload.PartitionFile;
import com.mcloud.service.cloudService.cloudServiceImpl.QcloudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vellerzheng on 2017/10/13.
 */
@Service
@Transactional
public class UploadFileServiceImpl implements UploadFileService{

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HashFileRepository hashFileRepository;
    @Autowired
    private MulCloudSDisposeService mulCloudSDisService;
    @Autowired
    private QcloudService qcloudService;

    /**
     *
     * @param path
     * @param pathPart
     * @param filename
     * @param fileSize
     * @param usrId
     * @return   newHashFileName   即为修改后的文件hash名.
     */
    @Override
    public String  dealFileUpload(String path,String pathPart,String filename,int fileSize,int usrId) {

        String srcPath =path+ File.separator+filename;
        /* 修改文件名为文件的hash值*/
        String newHashFileName =userRepository.findUsersEntityById(usrId).getUsername()+FileManage.getMD5ByFile(srcPath);
        String newFileNamePath = FileManage.renameFile(srcPath,newHashFileName);
       // String hashFileName = newFileName;
        //文件小于4M,仅仅单文件处理
        if(fileSize<=1024*1024*4){
            String encryptFile = path+File.separator+"encryptFile";
            File encryptFilePart = new File(encryptFile);
            //判断路径是否存在，如果不存在就创建一个
            if (!encryptFilePart.exists()) {
                encryptFilePart.mkdirs();
            }

            //文件加密
            FileEncAndDecByDES td = new FileEncAndDecByDES(userRepository.findUsersEntityById(usrId).getUsername());
            String encryptFilePath = encryptFile+File.separator+ new File(newFileNamePath).getName();
            try {
                td.encrypt(newFileNamePath,encryptFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 腾讯云，数据库编号2
            qcloudService.uploadFile(encryptFilePath);

            /*判断加密的文件路径是否存在，如果存在就删除*/
            if (encryptFilePart.exists()) {
                FileManage.deleteDirectory(encryptFile);
            }
        }else {
            int fs = fileSize / 1024 / 1024 / 4;     //  unit  MB  , each file after splited

            String encryptFile = path+File.separator+"encryptFile";
            File encryptFilePart = new File(encryptFile);
            //判断路径是否存在，如果不存在就创建一个
            if (!encryptFilePart.exists()) {
                encryptFilePart.mkdirs();
            }
            //文件加密
            FileEncAndDecByDES td = new FileEncAndDecByDES(userRepository.findUsersEntityById(usrId).getUsername());
            String encryptFilePath = encryptFile+File.separator+ new File(newFileNamePath).getName();
            try {
                td.encrypt(newFileNamePath,encryptFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }

                /* 文件分片*/
            PartitionFile partitionFile = new PartitionFile();
            boolean spt = partitionFile.split(encryptFilePath, fs, pathPart);

            /*判断加密的文件路径是否存在，如果存在就删除*/
            if (encryptFilePart.exists()) {
                FileManage.deleteDirectory(encryptFile);
            }
            // 多云上传
            if (spt) {
                mulCloudSDisService.getPartFilePath(pathPart);
                mulCloudSDisService.uploadPartFileToClouds();
            }
        }

        return newHashFileName;
    }

    @Override
    public void saveFileInfoToDateBase(String pathPart,String filename,String hashFileName,String description,int fileSize,int usrId) {
          List<FilesEntity> allUserFileEty= fileRepository.findFilesEntityByUserIdEndsWith(usrId);
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
        fsty.setCreatetime(CustomDateConverter.currentTime());
        fsty.setUpdatetime(CustomDateConverter.currentTime());
        UsersEntity usEnty= userRepository.findUsersEntityById(usrId);
        fsty.setUserByUserId(usEnty);
        fsty.setStatus(1);
        fsty.setVersion(1);
        fileRepository.saveAndFlush(fsty);
        int lastId = fileRepository.findLastIdFormFilesEntity();
        FileHashInteractionSql fHashInter = new FileHashInteractionSql(fileRepository.findOne(lastId),pathPart,hashFileName);
        fHashInter.saveHashFileNameToHashSql();
    }

    public class FileHashInteractionSql {
        private  int fileId;
        private FilesEntity filesEntity;
        private  String pathPart;
        private  String fileHashName;



        public FileHashInteractionSql(FilesEntity filesEntity,String pathPart,String fileHashName){
            this.filesEntity =filesEntity;
            this.pathPart =pathPart;
            this.fileHashName =fileHashName;
        }

        public  void saveHashFileNameToHashSql(){
            List<String> partFileNamePath = FileManage.getPartFileName(pathPart);
            FilesHashEntity filesHashEntity = new FilesHashEntity();
            filesHashEntity.setFileHash(fileHashName);
            if(partFileNamePath.size()==5) {
                filesHashEntity.setAliyunHash(partFileNamePath.get(0));
                filesHashEntity.setNeteaseHash(partFileNamePath.get(1));
                filesHashEntity.setQcloudHash(partFileNamePath.get(2));
                filesHashEntity.setQiniuHash(partFileNamePath.get(3));
                filesHashEntity.setUpyunHash(partFileNamePath.get(4));
            }else{
                String suffix = filesEntity.getFileName().substring(filesEntity.getFileName().lastIndexOf(".") + 1);
                String newFileHashNamePath =fileHashName+"."+suffix;
                filesHashEntity.setQcloudHash(newFileHashNamePath);
            }
      //      FilesEntity fty =fileRepository.findOne(fileId);
            filesHashEntity.setFilesIdByFileId(filesEntity);
            hashFileRepository.saveAndFlush(filesHashEntity);
        }

    }

}


