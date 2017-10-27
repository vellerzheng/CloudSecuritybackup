<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
--%>


<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script src="/js/jquery-1.11.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <title>Login</title>

    <script>
    function reloadValidateCode(){
    $("#validateCodeImg").attr("src","/validateCode?data=" + new Date() + Math.floor(Math.random()*24));
    }
    </script>

    <script type="text/javascript">

        function $(str){
            return (document.getElementById(str));
        }

        function check_submit(){
            if($("username").value =="" || $("username").value=="请输入用户名"){
                alert("用户名不能为空!");
                return false;
            }
            if($("password").value ==""){
                alert("密码不能为空！");
                return false;
            }
        }

        function mover(){
            event.srcElement.focus();
            event.srcElement.select();
        }

        function mclick(){
            if(event.srcElement.value=="请输入用户名")
                event.srcElement.value="";
        }

        function mblur(){
            if(event.srcElement.value=="")
                event.srcElement.value="请输入密码";
        }
    </script>

    <style>
    body {
        background: url("/images/ava/loginBg.jpg");
    }
    </style>

</head>

<body>
<div class="container col-md-8 col-sm-offset-2 text-center">
    <table class="table table-bordered table-striped">
        <tr>
            <form:form id="loginForm" action="/clouds/users/login" onsubmit="return check_submit();" modelAttribute="login"  method="post">
                <h2 class="form-signin-heading">请登录</h2>
                <hr/>
                <div class="form-group">
                    <label for="username">user name:</label>
                    <input type="text" class="from-control" id ="username" name="username" placeholder="Enter UserName:" onmouseover="mover();" onclick="mclick();"/>
                </div>
                <div class="form-group">
                    <label for="password">PassWord:</label>
                    <input type="text" class="from-control" id ="password" name="password" placeholder="Enter PassWord:" onmouseover="mover();" />
                </div>
 <%--              <div >
                    <li>验证码：<input type="text" name="validateCode" />
                        <img id="validateCodeImg" src="/validateCode"/>
                        <a href="#" onclick="javascript:reloadValidateCode();">看不清？</a></li>
                </div>--%>
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

</body>

</html>