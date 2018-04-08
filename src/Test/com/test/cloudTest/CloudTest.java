package com.test.cloudTest;

import com.mcloud.service.upload.fileToMulClouds.MulCloudsDispose;
import org.junit.Test;

/**
 * Created by vellerzheng on 2017/10/19.
 */
public class CloudTest {

    /**
     * 普通单线程测试耗时：  79575 ms
     *
     *
     */
    @Test
    public void mulCloudsUploadFileTest(){
        long startTime=System.currentTimeMillis();
        String uploadDirectory="D:\\Test\\split\\cloudStorageService.pdf";
        String downloadDirectory="D:\\Test\\merge";
        MulCloudsDispose mulCloudsDispose = new MulCloudsDispose();
        mulCloudsDispose.getPartFilePath(uploadDirectory);
        mulCloudsDispose.uploadPartFileToClouds();
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

    }
}
