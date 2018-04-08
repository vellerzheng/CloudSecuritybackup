package com.mcloud.controller;

import com.mcloud.model.FilesEntity;
import com.mcloud.repository.FileRepository;
import com.mcloud.repository.HashFileRepository;
import com.mcloud.service.DownloadFileService;
import com.mcloud.service.ManagementFileService;
import com.mcloud.service.UploadFileService;
import com.mcloud.util.common.FileManage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    HashFileRepository hashFileRepository;
    @Autowired
    DownloadFileService downloadFileService;
    @Resource(name="uploadFileServiceImpl")
    private UploadFileService uploadFileService;
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    /*用户上传文件*/
    @RequestMapping(value ="/clouds/filemanager/uploadfile/{id}", method = RequestMethod.GET)
    public String getUploadForm(@PathVariable("id") int uid, ModelMap modelMap){
        // UsersEntity usersEntity= userRepository.findUsersEntityById(uid);
        modelMap.addAttribute("authUsersEntity",uid);
        return "clouds/filemanager/uploadfile";
    }

    /*上传文件会自动绑定到MultipartFile中*/
    @RequestMapping(value="/clouds/filemanager/uploadfile/add",method = RequestMethod.POST)
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                         @RequestParam("description") String description, @RequestParam("curAuthUserEntity") int usrloginId, ModelMap modelMap) throws Exception {

        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //产生随机文件名防止重复
            String uploadDirectory = RandomStringUtils.randomAlphanumeric(10);
            //上传文件路径
            String path = request.getSession().getServletContext().getRealPath(uploadDirectory);
            //上传文件分块路径
            String pathPart =request.getSession().getServletContext().getRealPath(uploadDirectory)+File.separator+"filepart";
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }else{
                FileManage.deleteDirectory(pathPart);
                FileManage.deleteDirectory(path);
                filepath.getParentFile().mkdirs();
            }

            File filepathPart = new File(pathPart);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepathPart.exists()) {
                filepathPart.mkdirs();
            }

            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            modelMap.addAttribute("fileUrl", request.getContextPath()+uploadDirectory+filename);


            int fileSize = (int)file.getSize();
          //  uploadFileService.initUploadFile(path,pathPart,fileSize,description,filename,usrloginId);
            String hashFileName = uploadFileService.dealFileUpload(path,pathPart,filename,fileSize,usrloginId);
            uploadFileService.saveFileInfoToDateBase(pathPart,filename,hashFileName,description,fileSize,usrloginId);


            /*判断路径是否存在，如果存在就删除*/
            if (filepathPart.exists()) {
                FileManage.deleteDirectory(pathPart);
            }
            if (filepath.getParentFile().exists()) {
                FileManage.deleteDirectory(path);
            }
            modelMap.addAttribute("message","文件上传成功");
            return "redirect:/clouds/filemanager/files/"+usrloginId;
        } else {
            return "clouds/error";
        }

    }

    /* 查看所有文件*/
    @RequestMapping(value ="/clouds/filemanager/files/{id}", method = RequestMethod.GET)
    public String getFiles(@PathVariable("id") int id, ModelMap modelMap){
        List<FilesEntity> fileList = fileRepository.findFilesEntityByUserIdEndsWith(id);
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
        int hashFileId = hashFileRepository.findEntityByFileId(id).getId();
        manageFileService.deleteCloudFile(hashFileId);
        int usrId=fileRepository.findOne(id).getUserByUserId().getId();
        hashFileRepository.delete(hashFileId);
        hashFileRepository.flush();
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

    /* 下载文件 */
    @ResponseBody
    @RequestMapping(value="/clouds/filemanager/files/download/{file.id}/{file.fileName}")
    public   ResponseEntity<byte[]>  download(HttpServletRequest request, @PathVariable("file.id")int fid,
                                           @PathVariable("file.fileName") String filename,
                                           ModelMap modelMap)throws Exception {
        //产生下载随机文件名防止重复
        String downloadDirectory =RandomStringUtils.randomAlphanumeric(10);
        //上传文件路径
        String path = request.getSession().getServletContext().getRealPath(downloadDirectory);
        //上传文件分块路径
        String pathPart =request.getSession().getServletContext().getRealPath(downloadDirectory)+File.separator+"filepart";
     //   File filepath = new File(path,filename);
        //下载文件路径
        File file = new File(path + File.separator + filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        File filepathPart = new File(pathPart);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepathPart.exists()) {
            filepathPart.mkdirs();
        }

        //处理云文件下载与合并
        boolean downLoadRes = downloadFileService.downloadCloudFilePart(pathPart,fid);
        File   downLoadNewFile = downloadFileService.getRealFile(pathPart, path, fid);



         /* 将文件下载下来*/
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开文件
        headers.setContentDispositionFormData("attachment", downloadFileName);
        /*application/octet-stream ： 二进制流数据（最常见的文件下载）*/
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]>  resEty = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downLoadNewFile),
                headers, HttpStatus.OK);

        /*判断路径是否存在，如果存在就删除*/
        if (filepathPart.exists()) {
            FileManage.deleteDirectory(pathPart);
        }
        if (file.getParentFile().exists()) {
            FileManage.deleteDirectory(path);
        }
        return resEty;
    }
}
