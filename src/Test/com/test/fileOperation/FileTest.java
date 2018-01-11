package com.test.fileOperation;

import com.mcloud.service.supportToolClass.FileManage;
import org.junit.Test;

import java.io.File;

/**
 * Created by vellerzheng on 2017/10/19.
 */
public class FileTest {
    @Test
    public void testFileMd5(){
        String md5=null;
        String filePath ="D:\\Test\\split\\cloudStorageService.pdf";
        md5 = FileManage.getMD5ByFile(filePath);
        System.out.println(md5);

        File file = new File(filePath);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(suffix);

}

    @Test
    public void renameFile(){
        String filePath ="D:\\Test\\split\\cloudStorageService.pdf";
        FileManage.renameFile(filePath,FileManage.getMD5ByFile(filePath));
    }

    @Test
    public  void deleteFileTest() {
//  // 删除单个文件
//  String file = "c:/test/test.txt";
//  DeleteFileUtil.deleteFile(file);
//  System.out.println();
        // 删除一个目录
        String dir = "D:/home/web/upload/upload/files";
//        FileManage.deleteDirectory(dir);
//  System.out.println();
//  // 删除文件
//  dir = "c:/test/test0";
//  DeleteFileUtil.delete(dir);
    }
}
