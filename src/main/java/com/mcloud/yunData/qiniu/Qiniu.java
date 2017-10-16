package com.mcloud.yunData.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;


/**
 * Created by vellerzheng on 2017/8/24.
 */
public class Qiniu {

    private String accessKey = "yrQJe6hknpAgTL7Spe1x138FW0AaDMn6Vh8NaXVL";
    private String secretKey = "kHO2DI6nwbSSwoSL0SYLXVbwOfo50coEB6q-WMk0";
    private String bucket = "qiniufile";

    public void uploadFile(String localFilePath){
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传

//如果是Windows情况下，格式是 D:\\qiniu\\test.png
    //        String localFilePath = "D:\\Test\\split\\README.txt";
//默认不指定key的情况下，以文件内容的hash值作为文件名
            String fileName = localFilePath.substring((localFilePath.lastIndexOf("\\")));
            String key =  fileName.replace("\\","");  //key 为上传的文件名
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(localFilePath, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        }

        /*断点续传文件方式*/
    public void randomAcessUpLoadFile(String localFilePath){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
//...生成上传凭证，然后准备上传

//如果是Windows情况下，格式是 D:\\qiniu\\test.png
    //    String localFilePath = "D:\\Test\\split\\README.txt";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String fileName = localFilePath.substring((localFilePath.lastIndexOf("\\")));
        String key =  fileName.replace("\\","");  //key 为上传的文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
            try {
                Response response = uploadManager.put(localFilePath, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    public void urlDownLoadSource(String finalUrl,String fileName,String saveFilePath) throws IOException {
        URL url = null;
        try {
            url = new URL(finalUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();

        //获取自己数组
        byte[] getData = new byte[0];
        try {
            getData = readInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //文件保存位置
        File saveDir = new File(saveFilePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }

        System.out.println("info:"+url+" download success");
    }
    public void downLoadPublicFile(String fileName,String saveFilePath) throws IOException {
        String domainOfBucket = "http://ov6imccl2.bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        System.out.println(finalUrl);
        urlDownLoadSource(finalUrl,fileName,saveFilePath);

    }
    public void downLoadPrivateFile(String fileName, String saveFilePath) throws IOException {
        String domainOfBucket = "http://ov6imccl2.bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String privateUrl = String.format("%s/%s", domainOfBucket, encodedFileName);

        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(privateUrl, expireInSeconds);
        System.out.println(finalUrl);
        urlDownLoadSource(finalUrl,fileName,saveFilePath);
    }

    public void getYunFileInfomation(String yunFileName){
        Configuration cfg = new Configuration(Zone.zone2());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            FileInfo fileInfo = bucketManager.stat(bucket, yunFileName);
            System.out.println("YunFile hash:"+fileInfo.hash);
            System.out.println("YunFile size:"+fileInfo.fsize);
            System.out.println("YunFile mimeType:"+fileInfo.mimeType);
            System.out.println("YunFile putTime: "+fileInfo.putTime);
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }

    public void deleteCloudFile(String fileName){

        String yunfilePath=fileName;
        Configuration cfg = new Configuration(Zone.zone2());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, yunfilePath);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }


    }


    public static void main(String [] args) throws IOException {
        String localFilePath="D:\\Test\\split\\Hadoop，The Definitive Guide.pdf-2.dat";
        Qiniu qiniu = new Qiniu();
      //  qiniu.randomAcessUpLoadFile(localFilePath);
      //  qiniu.uploadFile(localFilePath);
        String saveFilePath="D:\\Test\\merge";
        String fileName = "README.txt";
        qiniu.downLoadPrivateFile(fileName,saveFilePath);
        qiniu.deleteCloudFile(fileName);
        String yunFileName ="README.txt";
      //  qiniu.getYunFileInfomation(yunFileName);
    }
}
