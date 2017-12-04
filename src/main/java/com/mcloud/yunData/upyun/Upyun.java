package com.mcloud.yunData.upyun;


import main.java.com.UpYun;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by vellerzheng on 2017/8/25.
 */
public class Upyun {
    private String bucketName ="youpaiyunfile";
    private String userName = "guyun";
    private String password ="qwe123456";


   private UpYun getClientUpyun(){
        // 可选属性1，是否开启 debug 模式，默认不开启
          UpYun upyun = new UpYun(bucketName, userName, password);
          upyun.setDebug(false);
        // 可选属性2，超时时间，默认 30s
          upyun.setTimeout(30);
          return upyun;
    }

    public  void createYunFilePath(){
        // 创建目录
        boolean result = getClientUpyun().mkDir("/up/tt");
    }

      // 文件上传
    public void uploadFile(String localFilePath){
        String fileName = localFilePath.substring((localFilePath.lastIndexOf(File.separator)));
        String yunfilePath = "/up/tt/"+ fileName.replace(File.separator,"");  //key 为上传的文件名
        File file = new File(localFilePath);
        boolean result4 = false;
        try {
            UpYun upyunload =getClientUpyun();
            upyunload.setContentMD5(UpYun.md5(file));     // 计算文件 MD5，如果文件太大或计算不便，可以不计算
            result4 = upyunload.writeFile(yunfilePath, file,true);
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
        boolean result = getClientUpyun().rmDir(yunFileName);
        System.out.println("Delete cloud file succeed!");
    }

    public void downloadFile(String fileName,String saveFilePath){
        String yunFileName = "/up/tt/"+fileName;
        File file= new File(saveFilePath);
        boolean result=getClientUpyun().readFile(yunFileName,file);
        if(result)
            System.out.println("Download file succeed!");
        else
            System.out.println("Download file failed!");
    }

    public void getFileInformation(String fileName){
        String yunFilePath = "/up/tt/"+fileName;
        Map<String,String> result = getClientUpyun().getFileInfo(yunFilePath);
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

   public void getSpaceCapacity(){
       long usage = getClientUpyun().getBucketUsage();
       System.out.println("BucketUsage:"+usage);
   }


    public static  void main(String[] args){
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

    }
}
