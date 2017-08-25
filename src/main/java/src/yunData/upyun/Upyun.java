package src.yunData.upyun;


import main.java.com.UpYun;

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
        upyun.setContentMD5(UpYun.md5(localFilePath));     // 计算文件 MD5，如果文件太大或计算不便，可以不计算
        boolean result4 = upyun.writeFile("/up/tt/README.txt", localFilePath);
        System.out.println("upload file successed!");
    }

    public void deleteYunFile(){

        // 删除目录
        boolean result = upyun.rmDir("/up/tt/");
    }

    public void downloadFile(String yunFileName){

    }

    public void getFileInformation(){
        Map<String,String> result = upyun.getFileInfo("/up/tt/README.txt");
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

   public void getSpaceCapacity(){
       long usage = upyun.getBucketUsage();
       System.out.println("BucketUsage:"+usage);
   }


    public static  void main(String[] args){
        String localFilePath="D:\\Test\\split\\README.txt";
        Upyun upyun=new Upyun();
        upyun.initUpyun();
        upyun.createYunFilePath();
        upyun.uploadFile(localFilePath);
        upyun.getFileInformation();
        upyun.getSpaceCapacity();

    }
}
