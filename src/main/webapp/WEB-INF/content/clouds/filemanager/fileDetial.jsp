<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC 博客管理</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-1.11.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="/clouds/users/top"></jsp:include>
<div class="container">
    <h1> 个人文件库</h1>
    <hr/>

    <h3>文件详情 </h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty filesDetial}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>files表为空，请<a href="/clouds/filemanager/files/add" type="button" class="btn btn-primary btn-sm">添加</a>
        </div>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty filesDetial}">
        <table class="table table-bordered table-striped">



                    <tr>
                        <th>ID</th>
                        <td>${filesDetial.id}</td>
                    </tr>

                    <tr>
                        <th>User</th>
                        <td>${filesDetial.userByUserId.username}</td>
                    </tr>

                    <tr>
                        <th>File Name </th>
                        <td>${filesDetial.fileName}</td>
                    </tr>

                    <tr>
                        <th>File Size</th>
                        <td>${filesDetial.size}</td>
                    </tr>

                    <tr>
                        <th>Description</th>
                        <td>${filesDetial.description}</td>
                    </tr>

                    <tr>
                        <th>Publish Date</th>
                        <td><fmt:formatDate value="${filesDetial.pubDate }" pattern="yyyy-MM-dd"/></td>
                    </tr>


        </table>
    </c:if>
</div>

</body>
</html>