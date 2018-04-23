package com.mcloud.service.upload;

import com.mcloud.repository.ConfAliyunRespository;
import com.mcloud.service.cloudService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by vellerzheng on 2017/10/2.
 */
@Service
public class MulCloudsDisposeServiceImpl implements MulCloudSDisposeService {
    @Autowired
    ConfAliyunRespository confAliyunRespository;

    @Autowired
    AliyunService aliyunService;
    @Autowired
    NeteaseService neteaseService;
    @Autowired
    QcloudService qcloudService;
    @Autowired
    QiniuServie qiniuServie;
    @Autowired
    UpyunService upyunService;

    private List<String> subdirtylist;

    @Override
    public List<String> getPartFilePath(String partFileDirectory){
        File file =new File(partFileDirectory);
        File[] fileList=file.listFiles();
        subdirtylist = new ArrayList<String>();

        for(int i = 0; fileList.length > i; i++){
            if(fileList[i].isFile()){
                subdirtylist.add(fileList[i].getPath());
            }
        }
        //路径排序防止linux 和windows下file.listFiles() 读取路径顺序不同
        subdirtylist.sort(String::compareTo);

        return subdirtylist;
    }

    @Override
    public boolean uploadPartFileToClouds(){
        if(subdirtylist.size()==5) {

            aliyunService.uploadFile(subdirtylist.get(0));
            neteaseService.uploadFile(subdirtylist.get(1));
            qcloudService.uploadFile(subdirtylist.get(2));
            qiniuServie.randomAcessUpLoadFile(subdirtylist.get(3));
            upyunService.uploadFile(subdirtylist.get(4));

            }else{
            System.out.println("file number not matched.");
            return false;
        }
        return true;

    }

}
