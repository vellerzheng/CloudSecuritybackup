package src.controller;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by vellerzheng on 2017/9/20.
 */
@Controller
public class FileUploadController {
    //上传文件会自动绑定到MultipartFile中
    @RequestMapping(value="/clouds/upload",method= RequestMethod.POST)
    public String upload(HttpServletRequest request,
                         @RequestParam("description") String description,
                         @RequestParam("file") MultipartFile file,ModelMap model) throws Exception {
        System.out.println("start!");
        System.out.println(description);
        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            /* MultipartFile 转 file*/
            CommonsMultipartFile cf =(CommonsMultipartFile)file;
            DiskFileItem fi=(DiskFileItem)cf.getFileItem();
            File f=fi.getStoreLocation();   /*会在项目的临时文件夹下生成一个文件*/

            //上传文件路径
            String path = request.getServletContext().getRealPath("upload");
            //上传文件名
            System.out.println(path);
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+filename);
            System.out.println("upload file finished!");
            //重定向地址  
            return "clouds/uploadResult";
        } else {
            return "clouds/error";
        }

    }
}
