package com.test.fileOperation;

import com.mcloud.service.supportToolClass.fileHandle.FileEncAndDecByDES;
import org.junit.Test;

public class FileEncAndDecTest {

    @Test
    public void fileEncAndEecTest1() throws Exception {

       FileEncAndDecByDES td = new FileEncAndDecByDES("kraft");
        td.encrypt("D:\\Test\\split\\mysql必知必会.pdf", "D:\\Test\\split\\mysql必知必会1.pdf"); //加密
/*
        //切割
        long startTime1=System.currentTimeMillis();   //获取开始时间
        String src = "D:\\Test\\split\\encryptFile\\mysql必知必会.pdf";
        File file= new File(src);
        int fileSize = (int)file.length()/1024/1024/4;     //  unit  MB  , each file after splited
        String dest = "D:\\Test\\split"+File.separator+"filepart";
        System.out.println("Split file start...");
        PartitionFile partitionFile= new PartitionFile();
        partitionFile.split(src,fileSize,dest);



        //合并
        String destPath ="D:\\Test\\merge\\mergedFile";
        // the file path should be merged
        List<String> srcPaths= new ArrayList<>();
                srcPaths.add("D:\\Test\\split\\filepart\\kraftba2c32489f0bb4bc50a8553a687f334a.pdf-0.dat");
                srcPaths.add("D:\\Test\\split\\filepart\\kraftba2c32489f0bb4bc50a8553a687f334a.pdf-1.dat");
                srcPaths.add("D:\\Test\\split\\filepart\\kraftba2c32489f0bb4bc50a8553a687f334a.pdf-2.dat");
                srcPaths.add("D:\\Test\\split\\filepart\\kraftba2c32489f0bb4bc50a8553a687f334a.pdf-3.dat");
                srcPaths.add("D:\\Test\\split\\filepart\\kraftba2c32489f0bb4bc50a8553a687f334a.pdf-4.dat");

        System.out.println("Start merging file...");
        PartitionFile partitionFile= new PartitionFile();
        partitionFile.merge(srcPaths,destPath);
        System.out.println("merge file finished!");
*/
        //解密
       FileEncAndDecByDES bt = new FileEncAndDecByDES("kraft");
        bt.decrypt("D:\\Test\\split\\mysql必知必会1.pdf", "D:\\Test\\merge\\mysql必知必会.pdf");

    }
}
