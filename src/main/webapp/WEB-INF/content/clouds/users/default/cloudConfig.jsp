<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

    <title>Registration</title>


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

            if($("Email").value ==""){
                alert("邮箱不能为空！");
                return false;
            }
            if($("Phone").value ==""){
                alert("电话不能为空！");
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

</head>

<body>

<div class="container col-md-8 col-sm-offset-2 text-center">
    <table class="table table-bordered table-striped">
        <div id="aliyunConf">
        <h1>阿里云初始化配置</h1>
        <hr/>
            <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <form id="userRegister" onsubmit="return check_submit();">
                    <div class="form-group">
                        <label for="username">user name:</label>
                        <input type="text" class="from-control" id ="username" name="username" onmouseover="mover();" onclick="mclick();"
                               placeholder="Enter UserName:"/>
                    </div>
                    <div class="form-group">
                        <label for="password">PassWord:</label>
                        <input type="text" class="from-control" id ="password" name="password" onmouseover="mover();" placeholder="Enter PassWord:"/>
                    </div>
                    <div class="form-group">
                        <label for="Email">Email:</label>
                        <input type="text" class="from-control" id ="email" name="email" onmouseover="mover();"  placeholder="Enter Email:"/>
                    </div>
                    <div class="form-group">
                        <label for="Phone">Phone: </label>
                        <input type="text" class="from-control" id ="phone" name="phone" onmouseover="mover();"  placeholder="Enter phone:"/>
                    </div>
                    <div class="form-group">
                        <input type ="button" name="submit"  class="btn btn-sm btn-success" onclick="userRegisterPost();" value="submit" />
                    </div>
                </form>
            </div>
            </div>
        </div>


    </table>

    <button  class="addlabel bigbutton"  onclick="addCloudConfig()" id="savebutton">确定</button>
    <button onclick="hide()" class="bigbutton">取消</button>


    <table align="center">
        <tr>


        </tr>

    </table>
</div>

</body>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>



    function hide() {
        login.style.display = "none";
        over.style.display = "none";
        $("#savebutton").removeAttr("disabled");
    }

</script>
</html>