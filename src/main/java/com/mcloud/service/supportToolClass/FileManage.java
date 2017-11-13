package com.mcloud.service.supportToolClass;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vellerzheng on 2017/10/2.
 */
public class FileManage {
    private static Logger logger = Logger.getLogger(FileManage.class);
    /**
     * 获得文件的MD5
     * @param filePath 文件路径
     */
    public static String getMD5ByFile(String filePath){
        String md5=null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
            IOUtils.closeQuietly(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return md5;
    }


    /**
     *  重命名文件,适用于上传文件名的修改为md5
     *  @param filePath 源文件路径
     *  @param newFileName 新文件名(不包含后缀)
     */
    public static String renameFile(String filePath, String newFileName) {

        File toBeRenamed = new File(filePath);
        String suffix = toBeRenamed.getName().substring(toBeRenamed.getName().lastIndexOf(".") + 1);
        String newFileNamePath =toBeRenamed.getParent()+File.separator+newFileName+"."+suffix;
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {

            logger.error("File does not exist: " + filePath);
            return filePath;
        }

        File newFile = new File(newFileNamePath);

        //修改文件名
        if (toBeRenamed.renameTo(newFile)) {
            return newFileNamePath;
        } else {
            logger.error("Error renmaing file");
            return filePath;
        }

    }

    /**
     * 重命名文件，适用于md5文件名转换为真正的文件名。
     *
     */
    public  static  File  md5FileNameToRealFilename(String filePath,String newFileName){

        String suffix = newFileName.substring(newFileName.lastIndexOf(".") + 1);
        String sourceFilePath = filePath+"."+suffix;
        File toBeRenamed = new File(sourceFilePath);
        String newFileNamePath =toBeRenamed.getParent()+File.separator+newFileName;
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {

            logger.error("File does not exist: " + filePath);
            return toBeRenamed;
        }

        File newFile = new File(newFileNamePath);

        //修改文件名
        if (toBeRenamed.renameTo(newFile)) {
            logger.debug("File has been renamed.");
            return newFile;
        } else {
            logger.error("Error renmaing file");
            return toBeRenamed;
        }

    }

    /**
     * 获取文件目录下所有的文件路径
     * @param partFileDirectory 文件夹路径
     * @return
     */
    public static List<String> getPartFileName(String partFileDirectory){
        File file =new File(partFileDirectory);
        File[] fileList=file.listFiles();
        List<String>  subdirtylist = new ArrayList<String>();

        for(int i = 0; fileList.length > i; i++){
            if(fileList[i].isFile()){
                subdirtylist.add(fileList[i].getName());
            }
        }
        return subdirtylist;
    }


    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            logger.error("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                logger.error("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            logger.error("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }


    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            logger.error("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileManage.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileManage.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            logger.error("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }



}
