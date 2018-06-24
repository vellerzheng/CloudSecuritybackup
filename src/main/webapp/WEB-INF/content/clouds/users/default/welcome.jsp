<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >

<html>

<head>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <title>Welcome</title>

</head>

<body>
<!-- 顶栏 -->
<jsp:include page="../../utils/account.jsp" flush="true"></jsp:include>

<div class="container">
    <table align="center">


        <hr/>
        <hr/>


    <tr>

        <!-- 如果用户列表为空 -->
        <c:if test="${empty loginUser}">
            <div class="alert alert-warning" role="alert">
                <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>恭喜注册成功，请 <a href="/clouds/users/login" type="button" class="btn btn-primary btn-sm">登陆</a>
            </div>
        </c:if>

        <td>
            <h2>  Welcome ${loginUser.username} </h2>
        </td>

        <!-- 如果用户列表非空 -->
        <c:if test="${!empty loginUser}">
            <table class="table table-bordered table-striped">



                <tr>
                    <th>选项</th>
                    <th>操作</th>
                </tr>

                <tr>
                    <th>配置多云服务链接初始化 </th>
                    <td>
                        <a href="/clouds/users/default/cloudConf/aliyunConfig" type="button" class="btn btn-sm btn-success">阿里云</a>
                        <a href="/clouds/users/default/cloudConf/neteaseConfig" type="button" class="btn btn-sm btn-success">网易云</a>
                        <a href="/clouds/users/default/cloudConf/qcloudConfig" type="button" class="btn btn-sm btn-success">腾讯云</a>
                        <a href="/clouds/users/default/cloudConf/qiniuConfig" type="button" class="btn btn-sm btn-success">七牛云</a>
                        <a href="/clouds/users/default/cloudConf/upyunConfig" type="button" class="btn btn-sm btn-success">又拍云</a>
                    </td>
                </tr>

                <tr>
                    <th>上传视频 </th>
                    <td><a href="/clouds/filemanager/uploadMedia/${loginUser.username}" type="button" class="btn btn-sm btn-success">Upload Media</a></td>
                </tr>

                <tr>
                    <th>上传文件 </th>
                    <td><a href="/clouds/filemanager/uploadfile/${loginUser.username}" type="button" class="btn btn-sm btn-success">Upload File</a></td>
                </tr>


                <tr>
                    <th>文件管理列表</th>
                    <td><a href="/clouds/filemanager/files/${loginUser.username}" type="button" class="btn btn-sm btn-success">File Manage</a></td>
                </tr>



            </table>
        </c:if>



        <br/>
        <br/>

        <td>
        </td>


    </tr>

</table>
</div>

</body>

</html>