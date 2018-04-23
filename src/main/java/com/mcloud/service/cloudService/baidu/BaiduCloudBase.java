package com.mcloud.service.cloudService.baidu;


import com.google.gson.Gson;
import com.mcloud.service.cloudService.baidu.entity.CloudInfo;
import com.mcloud.service.cloudService.baidu.entity.FileBase;
import com.mcloud.service.cloudService.baidu.entity.ListDir;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @author vellerzheng 获取百度开发所需的accesToken
 *         说明网址：http://developer.baidu.com/wiki/index.php
 *         ?title=docs/pcs/rest/file_data_apis_list ；
 *         最新的： token：
 *         3.92666f2d7ca22dc9f19372e38532346e
 *         .2592000.1371880919.1091082467-879176 user name:ydcun
 * */
public class BaiduCloudBase {



    /**
     * // @param URLConnection conn通过get方式获取StringBuffer
     * @return
     */
    private StringBuffer getJsonString(URLConnection conn) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            isr = new InputStreamReader(conn.getInputStream(),"gb2312");
            br = new BufferedReader(isr);
            String line = null;
            sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(isr!=null)
                    isr.close();
            } catch (IOException e) {
                System.out.println("流关闭是异常");
                e.printStackTrace();
            }
        }

        return sb;
    }

    /**
     * @return
     * @throws Exception
     *             获取云空间的信息
     */
    public CloudInfo getCloudInfo() throws Exception {
        URL u = new URL(Info.URL_INFO + "?method=" + Info.METHOD_INFO + "&access_token="+ Info.ACCESS_TOKEN);
        URLConnection conn = u.openConnection();// 打开网页链接
        // 获取用户云盘信息
        String cloudJson = this.getJsonString(conn).toString();

        // 解析成对象
        Gson gson = new Gson();
        CloudInfo cloudInfo = gson.fromJson(cloudJson, CloudInfo.class);
        System.out.println("云盘信息："+cloudInfo);
        return cloudInfo;
    }

    /**
     * @param path  下载的文件在云盘中的路径
     * @param name 文件名
     * @return
     * @throws Exception
     */
    public StringBuffer downFile(String path,String name,String savePath) throws Exception{
        path =path+name;
        if(savePath!=null)
            name= savePath+name;


        URL u = new URL(Info.URL_UPLOAD+"?path="+path+"&method="+Info.METHOD_DOWN+"&access_token="+Info.ACCESS_TOKEN);
        // 打开网页链接
        URLConnection conn = u.openConnection();
        StringBuffer fileBuffer = this.getJsonString(conn);
        //将文件从内存中输出
//		File saveFile = new File(name);
//		BufferedWriter buWriter = new BufferedWriter(new FileWriter(saveFile));
//		buWriter.write(fileBuffer.toString());
//		buWriter.flush();
        return fileBuffer;
    }


    /**
     * @param path 云盘存放路径
     * // @param name 要上传的文件
     * @return
     * @throws Exception
     */
    public FileBase uploadFile(String path, File file) throws Exception{
        //模拟文件
        String fileName="D:\\Test\\merge\\README.txt";
        file = new File(fileName);
        path=Info.PATH;//    "/apps/测试应用/"


        //将需要url传值的参数和url组装起来
       // String u =Info.URL_UPLOAD+"?method="+Info.METHOD_UPLOAD+"&access_token="+"&path="+path+file.getName()+Info.ACCESS_TOKEN+"&ondup="+Info.ONDUP_CMM;
        String u =Info.URL_UPLOAD+"?method="+Info.METHOD_UPLOAD+"&path="+path+file.getName()+"&access_token="+Info.ACCESS_TOKEN;
        URL url = new URL(u);
        System.out.println(u);


        PostMethod filePost = new PostMethod(u);
        //post提交的参数
        Part[] parts = {new FilePart(fileName,file)};
        //设置多媒体参数，作用类似form表单中的enctype="multipart/form-data"
        filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
        HttpClient clients = new HttpClient();
        //响应代码
        int status = clients.executeMethod(filePost);
        System.out.println("成功上传"+path+fileName);

        BufferedReader buReader = new BufferedReader(new InputStreamReader(filePost.getResponseBodyAsStream(),"utf-8"));
        StringBuffer sb = new StringBuffer();
        String line;
        while((line=buReader.readLine())!=null){
            sb.append(line);
        }
        buReader.close();

        // 解析成对象
        Gson gson = new Gson();
        FileBase cloudInfo = gson.fromJson(sb.toString(), FileBase.class);

        return cloudInfo;
    }


    /**
     * 获取指定目录中详细信息
     * @param path
     * @return
     * @throws Exception
     * */
    public ListDir getList(String path) throws Exception{
        path=Info.PATH;
        String u = Info.URL_UPLOAD+"?method=list&access_token="+Info.ACCESS_TOKEN+"&path="+path;
        URL url = new URL(u);
        URLConnection conn = url.openConnection();
        String jsonString = this.getJsonString(conn).toString();
        //将json串解析成对象（里面嵌套对象）
        ListDir listDir = new Gson().fromJson(jsonString, ListDir.class);
        return listDir;
    }

    /**
     * 创建目录
     * @param path
     * @return 200表示创建成功，400表示已经存在或错误请检查
     * @throws Exception
     */
    public int createDir(String path) throws  Exception{
        path=Info.PATH+"abc/123/ddd/aaa";

        String u=Info.URL_UPLOAD+"?method="+Info.METHOD_MKDIR+"&access_token="+Info.ACCESS_TOKEN+"&path="+path;
        PostMethod filePost = new PostMethod(u);
        HttpClient clients = new HttpClient();

        //下面是参数不是在请求头中的
//		filePost.setParameter("a123", "32");
        //响应代码
        int status = clients.executeMethod(filePost);
        return status;

    }
    /**
     * 获取单个文件/目录的元信息
     * @param path
     * @return
     * @throws Exception
     */
    public FileBase getOnleFileOrDir(String path) throws Exception{
        path=Info.PATH+"20130403.log";

        String u=Info.URL_UPLOAD+"?method="+Info.METHOD_META+"&access_token="+Info.ACCESS_TOKEN+"&path="+path;
        URL url = new URL(u);
        URLConnection conn = url.openConnection();// 打开网页链接
        // 获取单个文件/目录的元信息
        String fileJson = this.getJsonString(conn).toString();

        //将json串转换成对象
        Gson gson = new Gson();
        Map map = gson.fromJson(fileJson, Map.class);
        String listJson = gson.toJson(map.get("list"));
        FileBase fileBase = gson.fromJson(listJson.substring(1, listJson.length()-1), FileBase.class);

        return fileBase;
    }
    /**
     *
     *info.java文件中的云盘路径以及ticket、
     *
     * @throws Exception
     */
    public void csdnFriends() throws Exception{
        //获取云盘信息
        getCloudInfo();
        uploadFile("/",null);
    }
    public static void main(String[] args) throws Exception {
        BaiduCloudBase base = new BaiduCloudBase();

		base.uploadFile("/",null);
		base.downFile(Info.PATH,"README.txt",null);
		base.getCloudInfo();
		System.out.println(base.getList(null));
		base.createDir(null);
		base.getOnleFileOrDir(null);
        base.csdnFriends();

    }
}





