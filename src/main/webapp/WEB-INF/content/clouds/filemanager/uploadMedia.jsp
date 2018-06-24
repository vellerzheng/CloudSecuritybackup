<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- [endif] -->
<html lang="zh-CN">
<head>


    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Cloud Secure Storage</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/AJAX.js"></script>


    <title>文件上传</title>
    <script type="text/javascript">

        function $(str){
            return (document.getElementById(str));
        }

        function check_submit(){
            if($("exampleInputMedia").value =="" || $("exampleInputMedia").value=="请输入视频文件路径"){
                alert("文件不能为空!");
                return false;
            }

        }

        function mover(){
            event.srcElement.focus();
            event.srcElement.select();
        }

        function mclick(){
            if(event.srcElement.value=="请选择文件")
                event.srcElement.value="";
        }

        function mblur(){
            if(event.srcElement.value=="")
                event.srcElement.value="请输入提示信息";
        }

        function fileChange(target) {
            var fileSize = 0;
            var filetypes =[".jpg",".png",".bmp",".gif",".swf",".rar",".txt",".zip",".doc",".ppt",".xls",".pdf",".docx",".xlsx",".html",
                ".wav",".mp3",".midi",".avi",".mp4",".wmv",".mkv"];
            var filepath = target.value;
            var filemaxsize = 1024*1024*1;  //1G
            /*判断文件类型*/
            /*           if(filepath){
                           var isnext = false;
                           var fileend = filepath.substring(filepath.indexOf("."));
                           if(filetypes && filetypes.length>0){
                               for(var i =0; i<filetypes.length;i++){
                                   if(filetypes[i]==fileend){
                                       isnext = true;
                                       break;
                                   }
                               }
                           }
                           if(!isnext){
                               alert("不接受此文件类型！");
                               target.value ="";
                               return false;
                           }
                       }else{
                           return false;
                       }*/
            if (!target.files) {
                var filePath = target.value;
                var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
                if(!fileSystem.FileExists(filePath)){
                    alert("附件不存在，请重新输入！");
                    return false;
                }
                var file = fileSystem.GetFile (filePath);
                fileSize = file.Size;
            } else {
                fileSize = target.files[0].size;
            }

            var size = fileSize / 1024;
            if(size>filemaxsize){
                alert("附件大小不能大于"+filemaxsize/1024/1024+"G！");
                target.value ="";
                return false;
            }
            if(size<=0){
                alert("附件大小不能为0M！");
                target.value ="";
                return false;
            }
        }

    </script>

</head>
<body>
<!-- 顶栏 -->
<jsp:include page="../utils/account.jsp"></jsp:include>
<div class="container col-md-8 col-sm-offset-2 text-center">

    <table class="table table-bordered table-striped">
        <div id="tf-contact">
            <div class="container">

                <h2>监控视频上传配置</h2>
                <hr/>

                <div class="space"></div>
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <form id="contact" action="/clouds/filemanager/uploadMedia/video" onsubmit="return check_submit();" enctype="multipart/form-data" method="post" >
                            <div class="form-group">
                                <label for="exampleInputMedia">配置视频路径:</label>
                                <input type="text" class="form-control" id="exampleInputMedia" name="mediaPath"  placeholder="media path" onmouseover="mover();" onblur="mblur();">
                            </div>

                            <div>
                                <!-- 如果用户列表非空 -->
                                <c:if test="${!empty authUsersEntity}">
                                    <input type ="hidden"  class="form-control"  id="curAuthUserEntity" name="curAuthUserEntity" value="${authUsersEntity}"></input>
                                </c:if>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary my-btn dark">上传</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </table>
</div>


</body>
</html>
