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
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <title>Registration</title>
    <style type="text/css">

        .wrap{
            background:#F5F5F5;
            display:table;
            margin:0 auto;
        }

        #edit {
            text-align:center;
            display: none;
            border: 1px #FFCB00;
            height: 350px;
            width: 500px;
            position: absolute; /*让节点脱离文档流,我的理解就是,从页面上浮出来,不再按照文档其它内容布局*/
            top: 24%; /*节点脱离了文档流,如果设置位置需要用top和left,right,bottom定位*/
            left: 30%;
            z-index: 2; /*个人理解为层级关系,由于这个节点要在顶部显示,所以这个值比其余节点的都大*/
            background: white;
        }

        #over {
            width: 100%;
            height: 100%;
            opacity: 0.8; /*设置背景色透明度,1为完全不透明,IE需要使用filter:alpha(opacity=80);*/
            filter: alpha(opacity = 80);
            display: none;
            position: absolute;
            top: 0;
            left: 0;
            z-index: 1;
            background: silver;
        }
        .hid{
            display: none;
        }
        .labelstyle{
            width:50px;
            font-size: 14px;
            border-bottom:4px solid #ec971f;
            margin-bottom:-2px;
        }
        .cp:hover{cursor: pointer;}
        .labelsnonetyle{
            width:50px;
            font-size: 14px;
            margin-bottom:-2px;
        }
        #table2 th,#table1 th{
            text-align: center;
        }
    </style>

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

<!-- 顶栏 -->
<jsp:include page="../../../utils/account.jsp" flush="true"></jsp:include>

<hr>
<h2  class="wrap">多云链接初始化配置</h2>
<hr>

<div id="tf-contact">
    <div class="container">




        <div class="section-title col-md-6 col-md-offset-3">
            <h3>腾讯云初始化配置</h3>
            <p>配置腾讯云的链接，容器，密码，认证码等。</p>
            <hr>
        </div>
        <div id="qcloud" class="row">
            <div class="col-md-6 col-md-offset-3">
                <form:form id="qcloudConfig"  >
                    <div class="form-group">
                        <label for="AppId">AppId:</label>
                        <input type="text" class="form-control" id="appId" name="appId" placeholder="Enter appId" onmouseover="mover();"/>
                    </div>
                    <div class="form-group">
                        <label for="SecretId">SecretId:</label>
                        <input type="text" class="form-control" id="secretId" name="secretId" placeholder="Enter SecretId" onmouseover="mover();"/>
                    </div>
                    <div class="form-group">
                        <label for="SecretKey">SecretKey:</label>
                        <input type="text" class="form-control" id="secretKey" name="secretKey" placeholder="Enter SecretKey" onmouseover="mover();"/>
                    </div>
                    <div class="form-group">
                        <label for="BucketName">BucketName:</label>
                        <input type="text" class="form-control"  id="bucketName" name="bucketName" placeholder="Enter BucketName" onmouseover="mover();"/>
                    </div>
                </form:form>
            </div>
        </div>
        <br/>
        <br/>


        <div class="section-title col-md-8 col-md-offset-5">
            <button  class="btn btn-primary btn-success"  onclick="addCloudConfig('qcloudConfig','/js/AJAX.js/qcloudConfig')" id="savebutton">确定</button>
            <button  class="btn btn-primary  white"   onclick="hide()" class="bigbutton">取消</button>
        </div>

        <br/>
        <br/>
        <br/>
        <br/>


    </div>



</div>






<table align="center">
    <tr>


    </tr>

</table>
</div>

</body>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/AJAX.js"></script>
<script>



    function hide() {
        login.style.display = "none";
        over.style.display = "none";
        $("#savebutton").removeAttr("disabled");
    }

</script>
</html>