package com.mcloud.service.cloudService.cloudServiceImpl;


import com.mcloud.model.ConfUpyunEntity;
import com.mcloud.repository.ConfUpyunRespository;
import com.mcloud.service.cloudService.UpyunService;
import main.java.com.UpYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by vellerzheng on 2017/8/25.
 */
@Service
public class UpyunServiceImpl implements UpyunService {
/*    private String bucketName ="youpaiyunfile";
    private String userName = "guyun";
    private String password ="qwe123456";*/

    @Autowired
    private ConfUpyunRespository confUpyunRespository;
    private ConfUpyunEntity confUpyunEntity;

    private UpYun upYun;


    private void initUpyunClient(){
        // 可选属性1，是否开启 debug 模式，默认不开启
        if(confUpyunEntity == null)
            confUpyunEntity = confUpyunRespository.findOne(1);
        if(upYun == null ) {
            upYun = new UpYun(confUpyunEntity.getBucketName(), confUpyunEntity.getUserName(), confUpyunEntity.getPassword());
            upYun.setDebug(false);
            // 可选属性2，超时时间，默认 30s
            upYun.setTimeout(30);
        }

    }

    public  void createYunFilePath(){
        initUpyunClient();
        // 创建目录
        boolean result = upYun.mkDir("/up/tt");
    }

    // 文件上传
    public boolean uploadFile(String localFilePath){
        initUpyunClient();
        String fileName = localFilePath.substring((localFilePath.lastIndexOf(File.separator)));
        //key 为上传的文件名
        String yunfilePath = "/up/tt/"+ fileName.replace(File.separator,"");
        File file = new File(localFilePath);
        boolean result = false;
        try {
            // 计算文件 MD5，如果文件太大或计算不便，可以不计算
            upYun.setContentMD5(UpYun.md5(file));
            result = upYun.writeFile(yunfilePath, file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteYunFile(String fileName){
        initUpyunClient();
        String yunFileName = "/up/tt/"+fileName;
        // 删除目录
        boolean result = upYun.rmDir(yunFileName);
        return result;
    }

    public boolean downloadFile(String fileName,String saveFilePath){
        initUpyunClient();
        String yunFileName = "/up/tt/"+fileName;
        File file= new File(saveFilePath);
        boolean result=upYun.readFile(yunFileName,file);
        return result;
    }

    public void getFileInformation(String fileName){
        initUpyunClient();
        String yunFilePath = "/up/tt/"+fileName;
        Map<String,String> result = upYun.getFileInfo(yunFilePath);
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    public void getSpaceCapacity(){
        initUpyunClient();
        long usage = upYun.getBucketUsage();
        System.out.println("BucketUsage:"+usage);
    }


 /*   public static  void main(String[] args){
        String localFilePath="D:\\Test\\split\\云Hadoop，The Definitive Guide.pdf-4.dat";
        Upyun upyun=new Upyun();
      //  upyun.Upyun();
    //   upyun.createYunFilePath();
         upyun.uploadFile(localFilePath);

        String yunfile="云Hadoop，The Definitive Guide.pdf-4.dat";
        upyun.getFileInformation(yunfile);
        upyun.getSpaceCapacity();

        String saveFilePath="D:\\Test\\merge\\云Hadoop，The Definitive Guide.pdf-4.dat";
        upyun.downloadFile(yunfile,saveFilePath);
        String fileName="云Hadoop，The Definitive Guide.pdf-4.dat";
     //   upyun.deleteYunFile(fileName);

    }*/
}
