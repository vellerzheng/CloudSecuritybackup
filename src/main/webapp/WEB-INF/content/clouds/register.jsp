<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>



<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <title>Registration</title>

</head>

<body>

<div class="container">
    <h1>Cloud secure storage 注册用户</h1>
    <hr/>
    <form:form action="/clouds/register/add"  method="post" commandName="userRegister" role="form">

    <table align="center">
        <div class="form-group">
            <label for="username">user name:</label>
            <input type="text" class="from-control" id ="username" name="username" placeholder="Enter UserName:"/>
        </div>
        <div class="form-group">
            <label for="pwd">PassWord:</label>
            <input type="text" class="from-control" id ="pwd" name="pwd" placeholder="Enter PassWord:"/>
        </div>
        <div class="form-group">
            <label for="registration">Registration:</label>
            <input type="text" class="from-control" id ="registration" name="registration" placeholder="Enter Email or phone:"/>
        </div>
        <div class="form-group">
            <label for="registrationType">RegistrationType: </label>
            <input type="text" class="from-control" id ="registrationType" name="registrationType" placeholder="Enter email or phone:"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>

        <tr></tr>

        <tr>

            <td></td>

            <td><a href="home.jsp">Home</a>

            </td>

        </tr>

    </table>

</form:form>
</div>

<!--JQuerry 文件。 务必在bootstrap.min.js 之前引入-->
<script src="//cdn.boot.css.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>