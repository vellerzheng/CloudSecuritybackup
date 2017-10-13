package com.mcloud.yunData.baidu.entity;

/**
 * Created by vellerzheng on 2017/8/18.
 *保存上传数据后的返回值对象
 */
public class FileBase {
    private String path;	//该文件在云盘中的绝对路径
    private String size;	//文件字节大小
    private String ctime;	//文件创建时间
    private String mtime;	//文件修改时间
    private String md5;		//文件的md5签名
    private String fs_id;	//文件在PCS的临时唯一标识ID
    private String isdir;	//0是文件，1是目录

    private String block_list;	//文件所有分片的md5数组JSON字符串
    private String ifhassubdir; //是否含有子目录的标识符：0没有 1有



    /**
     * @return the block_list 文件所有分片的md5数组JSON字符串
     */
    public String getBlock_list() {
        return block_list;
    }
    /**
     * @param block_list the block_list to set 文件所有分片的md5数组JSON字符串
     */
    public void setBlock_list(String block_list) {
        this.block_list = block_list;
    }
    /**
     * @return the ifhassubdir 是否含有子目录的标识符：0没有 1有
     */
    public String getIfhassubdir() {
        return ifhassubdir;
    }
    /**
     * @param ifhassubdir the ifhassubdir to set 是否含有子目录的标识符：0没有 1有
     */
    public void setIfhassubdir(String ifhassubdir) {
        this.ifhassubdir = ifhassubdir;
    }
    /**
     * @return the isDir  0是文件，1是目录
     */
    public String getIsdir() {
        return isdir;
    }
    /**
     * @param isDir the isDir to set  0是文件，1是目录
     */
    public void setIsdir(String isdir) {
        this.isdir = isdir;
    }
    /**
     * @return the path该文件在云盘中的绝对路径
     */
    public String getPath() {
        return path;
    }
    /**
     * @param path the path to set该文件在云盘中的绝对路径
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * @return the size文件字节大小
     */
    public String getSize() {
        return size;
    }
    /**
     * @param size the size to set文件字节大小
     */
    public void setSize(String size) {
        this.size = size;
    }
    /**
     * @return the ctime文件创建时间
     */
    public String getCtime() {
        return ctime;
    }
    /**
     * @param ctime the ctime to set文件创建时间
     */
    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    /**
     * @return the mtime文件修改时间
     */
    public String getMtime() {
        return mtime;
    }
    /**
     * @param mtime the mtime to set文件修改时间
     */
    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
    /**
     * @return the md5文件的md5签名
     */
    public String getMd5() {
        return md5;
    }
    /**
     * @param md5 the md5 to set文件的md5签名
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    /**
     * @return the fs_id文件在PCS的临时唯一标识ID
     */
    public String getFs_id() {
        return fs_id;
    }
    /**
     * @param fs_id the fs_id to set文件在PCS的临时唯一标识ID
     */
    public void setFs_id(String fs_id) {
        this.fs_id = fs_id;
    }




}
