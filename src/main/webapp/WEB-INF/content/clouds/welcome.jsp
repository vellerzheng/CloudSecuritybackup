<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <title>Welcome</title>

</head>

<body>
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
                    <th>Upload File </th>
                    <td><a href="/clouds/filemanager/uploadfile" type="button" class="btn btn-sm btn-success">Upload File</a></td>
                </tr>


                <tr>
                    <th>File Manage</th>
                    <td><a href="/clouds/filemanager/files/${loginUser.id}" type="button" class="btn btn-sm btn-success">File Manage</a></td>
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
<!--JQuerry 文件。 务必在bootstrap.min.js 之前引入-->
<script src="//cdn.boot.css.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的Bootstrap 核心JavaScript 文件-->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>