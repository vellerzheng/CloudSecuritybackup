package src.yunData.qiniu;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;

/**
 * Created by vellerzheng on 2017/8/25.
 */
public class QiniuTest extends TestCase {
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testuploadFile() throws Exception {
        String localFilePath="D:\\Test\\split\\Hadoop，The Definitive Guide.pdf-3.dat";
        Qiniu qiniu = new Qiniu();
        qiniu.randomAcessUpLoadFile(localFilePath);
    }

    @Test
    public void testrandomAcessUpLoadFile() throws Exception {
        String localFilePath="D:\\Test\\split\\Hadoop，The Definitive Guide.pdf-4.dat";
        Qiniu qiniu = new Qiniu();
        qiniu.randomAcessUpLoadFile(localFilePath);
    }

    @Test
    public void testdownLoadPublicFile() throws Exception {
        Qiniu qiniu = new Qiniu();
        String saveFilePath="D:\\Test\\merge";
        qiniu.downLoadPublicFile(saveFilePath);
    }

    @Test
    public void testdownLoadPrivateFile() throws Exception {
        Qiniu qiniu = new Qiniu();
        String saveFilePath="D:\\Test\\merge";
        qiniu.downLoadPrivateFile(saveFilePath);
    }

    @Test
    public void testgetYunFileInfomation() throws Exception {
        Qiniu qiniu = new Qiniu();
        String yunFileName ="README.txt";
        qiniu.getYunFileInfomation(yunFileName);
    }

}