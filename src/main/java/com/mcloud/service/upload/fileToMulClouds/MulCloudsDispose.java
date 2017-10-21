package com.mcloud.service.upload.fileToMulClouds;

import com.mcloud.yunData.aliyun.AliyunOSS;
import com.mcloud.yunData.netease.Netease;
import com.mcloud.yunData.qcloud.Qcloud;
import com.mcloud.yunData.qiniu.Qiniu;
import com.mcloud.yunData.upyun.Upyun;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by vellerzheng on 2017/10/2.
 */
public class MulCloudsDispose {

    private List<String> subdirtylist;

    public List<String> getPartFilePath(String partFileDirectory){
        File file =new File(partFileDirectory);
        File[] fileList=file.listFiles();
        subdirtylist = new ArrayList<String>();

        for(int i = 0; fileList.length > i; i++){
            if(fileList[i].isFile()){
                subdirtylist.add(fileList[i].getPath());
            }
        }
        return subdirtylist;
    }

    public boolean uploadPartFileToClouds(){
        if(subdirtylist.size()==5) {
            AliyunOSS aliyunOSS = new AliyunOSS();
            aliyunOSS.uploadFile(subdirtylist.get(0));

            Netease netease =new Netease();
            netease.uploadFile(subdirtylist.get(1));

            Qcloud qcloud = new Qcloud();
            qcloud.uploadFile(subdirtylist.get(2));

            Qiniu qiniu = new Qiniu();
            qiniu.randomAcessUpLoadFile(subdirtylist.get(3));

            Upyun upyun =new Upyun();
            upyun.uploadFile(subdirtylist.get(4));

            }else{
            System.out.println("file number not matched.");
            return false;
        }
        return true;

    }

}
