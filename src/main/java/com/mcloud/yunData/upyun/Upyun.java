package com.mcloud.yunData.upyun;


import main.java.com.UpYun;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by vellerzheng on 2017/8/25.
 */
public class Upyun {
       private UpYun upyun = new UpYun("youpaiyunfile", "guyun", "qwe123456");


    public void initUpyun(){
        // 可选属性1，是否开启 debug 模式，默认不开启
          upyun.setDebug(false);
        // 可选属性2，超时时间，默认 30s
          upyun.setTimeout(30);
    }

    public  void createYunFilePath(){
        // 创建目录
        boolean result = upyun.mkDir("/up/tt");
    }

      // 文件上传
    public void uploadFile(String localFilePath){
        String fileName = localFilePath.substring((localFilePath.lastIndexOf("\\")));
        String yunfilePath = "/up/tt/"+ fileName.replace("\\","");  //key 为上传的文件名
        File file = new File(localFilePath);
        boolean result4 = false;
        try {
            upyun.setContentMD5(UpYun.md5(file));     // 计算文件 MD5，如果文件太大或计算不便，可以不计算
            result4 = upyun.writeFile(yunfilePath, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result4)
            System.out.println("upload file successed!");
        else
            System.out.println("upload file failed!");
    }

    public void deleteYunFile(String fileName){

        String yunFileName = "/up/tt/"+fileName;
        // 删除目录
        boolean result = upyun.rmDir(yunFileName);
    }

    public void downloadFile(String yunFileName,String saveFilePath){
        File file= new File(saveFilePath);
        boolean result=upyun.readFile(yunFileName,file);
        if(result)
            System.out.println("Download file succeed!");
        else
            System.out.println("Download file failed!");
    }

    public void getFileInformation(String yunFilePath){
        Map<String,String> result = upyun.getFileInfo(yunFilePath);
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

   public void getSpaceCapacity(){
       long usage = upyun.getBucketUsage();
       System.out.println("BucketUsage:"+usage);
   }


    public static  void main(String[] args){
        String localFilePath="D:\\Test\\split\\Hadoop，The Definitive Guide.pdf";
        Upyun upyun=new Upyun();
        upyun.initUpyun();
    //   upyun.createYunFilePath();
    //    upyun.uploadFile(localFilePath);

        String yunfile="/up/tt/Deskbookpicture2.jpg";
        //   upyun.getFileInformation(yunfile);
        upyun.getSpaceCapacity();

        String saveFilePath="D:\\Test\\merge\\Hadoop，The Definitive Guide.pdf";
      //  upyun.downloadFile(yunfile,saveFilePath);
        String fileName="Deskbookpicture2.jpg";
        upyun.deleteYunFile(fileName);

    }
}
