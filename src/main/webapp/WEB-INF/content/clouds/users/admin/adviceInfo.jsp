<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <<title>Cloud Secure Storage</title>
    <!-- 引入bootstrap -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- [endif]-->
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="../top.jsp"></jsp:include>

<div class="container">
    <h1> 文件系统-文件管理</h1>
    <hr/>

<%--    <h3>所有文件 <a href="/clouds/itemmanager/uploaditem/${loginId}" type="button" class="btn btn-primary btn-sm">添加文件</a></h3>
    <h4>                                                ${message}</h4>
    <!-- 如果用户列表为空 -->
    <c:if test="${empty pageAdviceEty}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>items表为空，请<a href="/clouds/itemmanager/items/add" type="button" class="btn btn-primary btn-sm">添加</a>
        </div>
    </c:if>--%>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty adviceLists}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>文件名</th>
                <th>描述</th>
                <th>文件大小</th>
                <th>上传日期</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${adviceLists}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.mainIdea}</td>
                    <td>${item.messageDetail}</td>
                    <td><fmt:formatDate value="${item.submitTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a href="/clouds/itemmanager/items/show/${item.id}" type="button" class="btn btn-sm btn-success">详情</a>
                        <a href="/clouds/itemmanager/items/update/${item.id}" type="button" class="btn btn-sm btn-warning">修改</a>
                        <a href="/clouds/itemmanager/items/delete/${item.id}" type="button" class="btn btn-sm btn-danger">删除</a>
                        <a href="/clouds/itemmanager/items/download/${item.id}" type="button" class="btn btns-sm btn-info">下载</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>

<%--<jsp:include page="../../pluggablePage/page.jsp"></jsp:include>--%>

</body>
</html>