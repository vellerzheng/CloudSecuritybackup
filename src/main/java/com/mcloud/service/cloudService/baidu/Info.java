package com.mcloud.service.cloudService.baidu;


/**
 * Created by vellerzheng on 2017/8/18.
 * 参数参考http://developer.baidu.com/wiki/index.php?title=docs/pcs/rest/file_data_apis_list
 *mybaidu token:21.5d2c53417637b79785c2090c3d2156a7.2592000.1505635233.623615740-10023469
 *测试应用 token:3.a2250ddd95ad14ac620148e632ab4b2f.2592000.1371890458.1091082467-248414
 */
public class Info {
    /**
     * 应用的路径 "/apps/public/"
     */
    public static String PATH="%2fapps%2fpcstest_oauth%2f";
    //  String PATH="%2fapps%2fBrance%2f";//csdnFriendsOX
    /**
     * 链接的token令牌
     */
    public static String ACCESS_TOKEN = "21.5d2c53417637b79785c2090c3d2156a7.2592000.1505635233.623615740-10023469";// token
    //   String ACCESS_TOKEN = "3.57b698cd9f99d59c4c2ba9d990eca675.2592000.1379126771.1344495316-1176036";//csdnFriendsOX
    /**
     * 获取云盘信息时的method参数值
     */
    public static String METHOD_INFO="info";
    /**
     * 上传文件时METHOD的参数值
     */
    public static String METHOD_UPLOAD="createsuperfile";
    /**
     * 下载文件时的METHOD参数值
     */
    public static String METHOD_DOWN="download";
    /**
     * 获取目录内文件/目录列表时的METHOD参数值
     */
    public static String METHOD_LIST="list";

    /**
     * 创建目录时method参数值
     */
    public static String METHOD_MKDIR="mkdir";
    /**
     * 获取单个文件或目录的详细信息中method参数值
     */
    public static String METHOD_META="meta";
    /**
     *
     */
    public static String METHOD_METH="move";



    /**
     * 获取云盘空间的URL
     */
    public static String URL_INFO = "https://pcs.baidu.com/rest/2.0/pcs/quota";
    /**
     * 上传/下载/目录文件时的URL
     */
    public static String URL_UPLOAD = "https://pcs.baidu.com/rest/2.0/file";


    /**
     * 遇到重复文件覆盖ondup参数的值
     */
    public static String ONDUP_FG="overwrite";
    /**
     * 遇到重复文件重命名    命名规则为    （文件名_日期.后缀）
     */
    public static String ONDUP_CMM="newcopy";
    /**
     * 获取文件夹目录的排序方式 time
     */
    public static String BY_TIME ="time";
    /**
     * v获取文件夹目录的排序方式 name
     */
    public static String BY_NAME ="name";
    /**
     * 获取文件夹目录的排序方式 size
     */
    public static String BY_SIZE ="size";
}
