package src.service.upload.deliverFile;

import java.io.*;

/**
 * Created by vellerzheng on 2017/8/14.
 */
public class PartitionFile {
    /**
     * cut file
     * @param src patch of source file
     * @param fileSize  the size after devision , MB
     * @param dest patch of target file
     */
    public  boolean split(String src,int fileSize,String dest){
        if("".equals(src)|| src==null ||fileSize==0 || "".equals(dest)||dest==null){
            System.out.println("分割失败！");
        }
        File srcFile =new File(src);
        long srcSize=srcFile.length();  //source file size
        long destSize=1024*1024*fileSize;  // target file size

        int number=(int)(srcSize/destSize);
        number = srcSize%destSize==0? number:number+1; // the file number finished devision

        String fileName = src.substring((src.lastIndexOf("\\")));  // the name of source file
        InputStream in = null;
        BufferedInputStream bis =null; // input cache stream
        byte[] bytes = new byte[1024*1024];
        int len=-1; // the length of each reading
        try {
            in = new FileInputStream(srcFile);
            bis = new BufferedInputStream(in);
            for(int i=0;i<number;i++){
                String destName = dest+File.separator+fileName+"-"+i+".dat";
                OutputStream out = new FileOutputStream(destName);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int count =0;
                while((len=bis.read(bytes))!=-1){
                    bos.write(bytes,0,len);
                    count+=len;
                    if(count>=destSize){
                        break;
                    }
                }
                bos.flush();
                bos.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            // close stream
            try{
                if(bis!=null) bis.close();
                if(in!= null) in.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     *  combine file
     *  @param destPath  target file path
     *  @param srcPaths   source file Path
     */
    public  void merge(String destPath,String ...srcPaths){
        if(destPath==null|| "".equals(destPath)||srcPaths==null){
            System.out.println("merge file fail!");
        }
        for(String string :srcPaths){
            if("".equals(string)||string==null){
                System.out.println("merge file fail!");
            }
        }

        //the file name after merged
        String name = srcPaths[0].substring((srcPaths[0].lastIndexOf("\\")));
        String destName = name.substring(0,name.lastIndexOf("-"));
        destPath = destPath+destName; //  the file path after merged

        File destFile = new File(destPath); // the file after merged
        OutputStream out =null;
        BufferedOutputStream bos = null;
        try {
            out = new FileOutputStream(destFile);
            bos = new BufferedOutputStream(out);
            for(String src:srcPaths) {
                File srcFile = new File(src);
                InputStream in = new FileInputStream(srcFile);
                BufferedInputStream bis = new BufferedInputStream(in);
                byte[] bytes = new byte[1024*1024];
                int len =-1;
                while((len=bis.read(bytes))!=-1){
                    bos.write(bytes,0,len);
                }
                bis.close();
                in.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(bos!=null) bos.close();
                if(out!=null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){
        /*
         * test cut file
         */

        long startTime1=System.currentTimeMillis();   //获取开始时间
        String src = "D:\\Test\\split\\Hadoop，The Definitive Guide.pdf";
        File file= new File(src);
        int fileSize = (int)file.length()/1024/1024/4;     //  unit  MB  , each file after splited
        String dest = "D:\\Test\\split";
        System.out.println("Split file start...");
        PartitionFile partitionFile= new PartitionFile();
        partitionFile.split(src,fileSize,dest);
        System.out.println("Split file finished!");
        long endTime1=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序split()运行时间： "+(endTime1-startTime1)+"ms");


        /**
         * merge file test
         */
/*
        long startTime=System.currentTimeMillis();   //获取开始时间
        String destPath ="D:\\Test\\merge";
        // the file path should be merged
        String[] srcPaths={
               "D:\\Test\\split\\[阳光电影www.ygdy8.com].星际穿越.BD.720p.中英双字幕.rmvb-0.dat",
                "D:\\Test\\split\\[阳光电影www.ygdy8.com].星际穿越.BD.720p.中英双字幕.rmvb-1.dat",
                "D:\\Test\\split\\[阳光电影www.ygdy8.com].星际穿越.BD.720p.中英双字幕.rmvb-2.dat",
                "D:\\Test\\split\\[阳光电影www.ygdy8.com].星际穿越.BD.720p.中英双字幕.rmvb-3.dat",
        };
        System.out.println("Start merging file...");
        partitionFile.merge(destPath,srcPaths);
        System.out.println("merge file finished!");
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
 */
    }
}
