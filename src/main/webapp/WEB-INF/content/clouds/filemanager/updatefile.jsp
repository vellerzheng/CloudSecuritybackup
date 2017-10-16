<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC 修改博客</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>  修改文件信息 </h1>
    <hr/>
    <form:form action="/clouds/filemanager/files/updateP" method="post" commandName="filePt" role="form">
        <div class="form-group">
            <label for="fileName">File Name:</label>
            <input type="text" disabled  class="form-control" id="fileName" name="fileName"
                   placeholder="${fileEty.fileName}" value="${fileEty.fileName}"/>
        </div>

        <div class="form-group">
            <label for="size">File Size:</label>
            <input type="text" disabled class="form-control" id="size" name="size"
                      placeholder="${fileEty.size}" value="${fileEty.size}" />
        </div>
        <div class="form-group">
            <label for="description">Content:</label>
            <textarea class="form-control" id="description" name="description" rows="3"
                      placeholder="Please Input Content"> ${fileEty.description}</textarea>
        </div>

        <div class="form-group">
            <label for="pubDate">Publish Date:</label>
            <input type="date" class="form-control" id="pubDate" name="pubDate"
                   value="<fmt:formatDate value="${fileEty.pubDate}" pattern="yyyy-MM-dd"/>"/>
        </div>
        <div class="form-group">
            <input type="hidden" class="form-control" id="userByUserId.id" name="userByUserId.id"
                   value="${fileEty.userByUserId.id}" />
        </div>
        <!-- 把 id 一并写入 fileP 中 -->
        <input type="hidden" id="id" name="id" value="${fileEty.id}"/>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>