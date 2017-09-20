<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Cloud Storage </title>
<!--新 Bootstrap 核心 CSS 文件-->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

<!-- [endif] -->
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <title>文件上传</title>
</head>
<body>
    <h2>文件上传</h2>
    <form action="/clouds/upload" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>文件描述:</td>
                <td><input type="text" name="description"></td>
            </tr>
            <tr>
                 <td>请选择文件:</td>
                 <td><input type="file" name="file"></td>
            </tr>
            <tr>
                 <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
    <!--JQuerry 文件。 务必在bootstrap.min.js 之前引入-->
    <script src="//cdn.boot.css.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- 最新的Bootstrap 核心JavaScript 文件-->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>
