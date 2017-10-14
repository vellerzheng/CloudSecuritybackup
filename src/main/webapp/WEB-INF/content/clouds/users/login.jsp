<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>

<head>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <title>Login</title>

</head>

<body>
<div class="container col-md-8 col-sm-offset-2 text-center">
    <table class="table table-bordered table-striped">
        <tr>
            <form:form id="loginForm" action="/clouds/users/login/auth" modelAttribute="login"  method="post">
                <h2 class="form-signin-heading">请登录</h2>
                <hr/>
                <div class="form-group">
                    <label for="username">user name:</label>
                    <input type="text" class="from-control" id ="username" name="username" placeholder="Enter UserName:"/>
                </div>
                <div class="form-group">
                    <label for="password">PassWord:</label>
                    <input type="text" class="from-control" id ="password" name="password" placeholder="Enter PassWord:"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-sm btn-success">登录</button>
                </div>
            </form:form>
        </tr>

    </table>

    <table align="center">
        <tr></tr>

        <tr>

            <td></td>

            <td>
                <a href="/clouds/home" type="button" class="btn btn-sm btn-info">Home</a>
            </td>

        </tr>



        <tr>

          <!--  <td style="font-style: italic; color: red;">${message}</td> -->

        </tr>

    </table>

</div>
<!--JQuerry 文件。 务必在bootstrap.min.js 之前引入-->
<script src="//cdn.boot.css.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的Bootstrap 核心JavaScript 文件-->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>

</html>