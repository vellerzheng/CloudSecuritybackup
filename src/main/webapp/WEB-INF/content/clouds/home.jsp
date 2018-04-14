<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Cloud Secure Storage</title>
    <!-- 引入bootstrap -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- [endif] -->
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="utils/account.jsp"></jsp:include>

<div class="container">
    <h1 style="text-align: center"> Cloud Secure Storage </h1>
    <hr/>
    <table align="center">

        <tr>

            <td><a href="/clouds/users/login"  type="button" class="btn btn-sm btn-success">Login</a>

            </td>

            <td>    </td>

            <td><a href="/clouds/users/register" type="button" class="btn btn-sm btn-info">Register</a>

            </td>

        </tr>

    </table>

</div>
</body>
</html>
