package com.mcloud.controller;

import com.mcloud.model.FilesEntity;
import com.mcloud.repository.FileRepository;
import com.mcloud.service.ManagementFileService;
import com.mcloud.service.UploadFileService;
import com.mcloud.service.supportToolClass.FileManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by vellerzheng on 2017/9/20.
 */
@Controller
public class FileController {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    ManagementFileService manageFileService;

    @Resource(name="uploadFileServiceImpl")
    private UploadFileService uploadFileService;

    /*用户上传文件*/
    @RequestMapping(value ="/clouds/filemanager/uploadfile/{id}", method = RequestMethod.GET)
    public String getUploadForm(@PathVariable("id") int uid, ModelMap modelMap){
        // UsersEntity usersEntity= userRepository.findUsersEntityById(uid);
        modelMap.addAttribute("authUsersEntity",uid);
        return "clouds/filemanager/uploadfile";
    }

    /*上传文件会自动绑定到MultipartFile中*/
    @RequestMapping(value="/clouds/filemanager/uploadfile/add",method = RequestMethod.POST)
    public String upload(HttpServletRequest request,@RequestParam("file") MultipartFile file,
                         @RequestParam("description") String description,@RequestParam("curAuthUserEntity") int usrloginId, ModelMap modelMap) throws Exception {
        System.out.println("start!");
        System.out.println(description);
        System.out.println(usrloginId);
        /*还需要判断文件是否大于4M */
        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("upload");
            //上传文件分块路径
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
            modelMap.addAttribute("fileUrl", request.getContextPath()+"/upload/"+filename);
            System.out.println("upload file finished!");

            int fileSize = (int)file.getSize();
            uploadFileService.initUploadFile(path,pathPart,fileSize,description,filename,usrloginId);
            uploadFileService.dealFileUpload();
            uploadFileService.saveFileInfoToDateBase();

            /*判断路径是否存在，如果存在就删除*/
            if (filepathPart.exists()) {
                FileManage.deleteDirectory(pathPart);
            }
            if (filepath.getParentFile().exists()) {
                FileManage.deleteDirectory(path);
            }
            modelMap.addAttribute("upFileResult","文件上传成功");
            return "redirect:/clouds/filemanager/files/"+usrloginId;
        } else {
            return "clouds/error";
        }

    }

    /* 查看所有文件*/
    @RequestMapping(value ="/clouds/filemanager/files/{id}", method = RequestMethod.GET)
    public String getFiles(@PathVariable("id") int id, ModelMap modelMap){
        List<FilesEntity> fileList = fileRepository.findByFilesEntityEEndsWith(id);
        modelMap.addAttribute("loginId",id);
        modelMap.addAttribute("fileList",fileList);
        return "clouds/filemanager/files";
    }

    /*查看单个文件详情*/
    @RequestMapping(value ="/clouds/filemanager/files/show/{id}",method = RequestMethod.GET)
    public String showFiles(@PathVariable("id") int id ,ModelMap modelMap){
        FilesEntity filesDetial = fileRepository.findOne(id);

        modelMap.addAttribute("filesDetial",filesDetial);
        return "clouds/filemanager/fileDetial";
    }

    /*删除云文件及记录*/
    @RequestMapping(value = "/clouds/filemanager/files/delete/{file.id}",method = RequestMethod.GET)
    public String deleteFile(@PathVariable("file.id") int id){
        manageFileService.initManagementFileService(id);
        manageFileService.deleteCloudFile();
        int usrId=fileRepository.findOne(id).getUserByUserId().getId();
        fileRepository.delete(id);
        fileRepository.flush();
        return "redirect:/clouds/filemanager/files/"+usrId;
    }

    /* 修改文件信息，页面*/
    @RequestMapping("/clouds/filemanager/files/update/{id}")
    public String updateFile(@PathVariable("id")int id, ModelMap modelMap){
        FilesEntity filesEntity = fileRepository.findOne(id);
        modelMap.addAttribute("fileEty",filesEntity);
        return "/clouds/filemanager/updatefile";
    }

    /* 修改文件信息，post请求 */
    @RequestMapping(value = "/clouds/filemanager/files/updateP", method = RequestMethod.POST)
    public String updateFilePt(@ModelAttribute("filePt") FilesEntity filesEntity){
        fileRepository.updateFiles(filesEntity.getDescription(), filesEntity.getPubDate(), filesEntity.getUserByUserId().getId(), filesEntity.getId());
        fileRepository.flush();
        return "redirect:/clouds/filemanager/files/" + filesEntity.getUserByUserId().getId();
    }
}
