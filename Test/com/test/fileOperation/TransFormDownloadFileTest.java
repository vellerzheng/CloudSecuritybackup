package com.test.fileOperation;

import com.mcloud.service.download.TransformDownloadFile;
import org.junit.Test;

public class TransFormDownloadFileTest {
    @Test
    public void testGetPartFilePath(){
        String path1="D:\\Test\\split";
        String path2="D:\\Test\\merge";
        TransformDownloadFile ts1=new TransformDownloadFile();
        ts1.getPartFilePath(path1);
        TransformDownloadFile ts2=new  TransformDownloadFile();
        ts2.getPartFilePath(path2);
        return;

    }
}
