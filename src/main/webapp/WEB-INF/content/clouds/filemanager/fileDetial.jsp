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
    <title>Cloud Secure Storage</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="../utils/account.jsp"></jsp:include>
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
                        <th>Create Time</th>
                        <td><fmt:formatDate value="${filesDetial.createtime }" pattern="yyyy-MM-dd"/></td>
                    </tr>

                    <tr>
                        <th>Update Time</th>
                        <td><fmt:formatDate value="${filesDetial.updatetime }" pattern="yyyy-MM-dd"/></td>
                    </tr>

                    <tr>
                        <th>Status</th>
                        <td>
                          <%--  ${filesDetial.status}--%>
                            <c:if test="${filesDetial.status eq -1}">上传失败</c:if>
                            <c:if test="${filesDetial.status eq 1}">上传成功</c:if>
                            <c:if test="${filesDetial.status eq 2}">正在上传</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>Version</th>
                        <td>${filesDetial.version}</td>
                    </tr>


        </table>
    </c:if>
</div>

</body>
</html>