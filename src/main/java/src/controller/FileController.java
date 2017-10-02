package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import src.repository.UserRepository;
import src.service.supportToolClass.FileManage;
import src.service.upload.fileToMulClouds.MulCloudsDispose;
import src.service.upload.deliverFile.PartitionFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by vellerzheng on 2017/9/20.
 */
@Controller
public class FileController {

    @Autowired
    UserRepository userRepository;


    //上传文件会自动绑定到MultipartFile中
    @RequestMapping(value="/clouds/uploadfile/add",method = RequestMethod.POST)
    public String upload(HttpServletRequest request,
                         @RequestParam("description") String description,
                         @RequestParam("file") MultipartFile file,ModelMap model) throws Exception {
        System.out.println("start!");
        System.out.println(description);
        /*还需要判断文件是否大于4M */
        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            /* MultipartFile 转 file */
       //     CommonsMultipartFile cf =(CommonsMultipartFile)file;
        //    DiskFileItem fi=(DiskFileItem)cf.getFileItem();
      //      File f=fi.getStoreLocation();   /*会在项目的临时文件夹下生成一个文件*/

            //上传文件路径
            String path = request.getServletContext().getRealPath("upload");
            String pathPart =request.getServletContext().getRealPath("upload")+"\\filepart";
            //上传文件名
            System.out.println(path);
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }

            File filepathPart = new File(pathPart);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepathPart.exists()) {
                filepathPart.mkdirs();
            }


            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+filename);
            System.out.println("upload file finished!");

            /* 文件分片*/
            int fileSize = (int)file.getSize()/1024/1024/4;     //  unit  MB  , each file after splited
            String srcPath =path+"\\"+filename;
            PartitionFile partitionFile= new PartitionFile();
            boolean spt = partitionFile.split(srcPath,fileSize,pathPart);

            /*多云上传*/
            if(spt) {
                MulCloudsDispose mulCloudsDispose = new MulCloudsDispose();
                mulCloudsDispose.getPartFilePath(pathPart);
                mulCloudsDispose.uploadPartFileToClouds();
            }
            //判断路径是否存在，如果不存在就创建一个
            if (filepathPart.exists()) {
                FileManage.deleteDirectory(pathPart);
            }
            if (filepath.getParentFile().exists()) {
                FileManage.deleteDirectory(path);
            }

            return "clouds/uploadResult";
        } else {
            return "clouds/error";
        }

    }
}
