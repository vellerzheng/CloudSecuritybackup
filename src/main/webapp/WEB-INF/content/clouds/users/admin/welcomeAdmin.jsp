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
<jsp:include page="./adminAccount.jsp" flush="true"></jsp:include>

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
                    <th>Advice & Information Manage</th>
                    <td><a href="/clouds/users/admin/adviceInfo/list" type="button" class="btn btn-sm btn-success"> Advice & INfo Manage</a></td>
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

</body>

</html>