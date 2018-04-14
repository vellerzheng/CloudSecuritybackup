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
<jsp:include page="./adminAccount.jsp"></jsp:include>

<div class="container">
    <h1> 用户系统-用户建议管理</h1>
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
                <th>用户名</th>
                <th>邮件</th>
                <th>主题</th>
                <th>详细情况</th>
                <th>上传日期</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${adviceLists}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.email}</td>
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

    <!-- 翻页部分 -->
    <jsp:include page="../../utils/page.jsp"></jsp:include>


</div>



</body>

</html>

<script type="text/javascript">


    //上一页
    function controlPagePre(){
        var nowPageNo = $("#nowPageNo1").val();
        if(parseInt(nowPageNo)-1<=0){
            controlPages(1,10);
        }else{
            controlPages(parseInt(nowPageNo)-1,10);
        }
    }
    //下一页
    function controlPageNext(){
        var nowPageNo = $("#nowPageNo1").val();
        var lastPageNo = $("#lastPageNo").val();
        if(parseInt(nowPageNo)+1>lastPageNo){
            controlPages(lastPageNo,10);
        }else{
            controlPages(parseInt(nowPageNo)+1,5);
        }
    }
    //末页
    function controlPageEnd(){
        var lastPageNo = $("#lastPageNo").val();
        controlPages(lastPageNo,5);
    }
    //首页
    function controlPageHead(){
        controlPages(1,5);
    }
    //跳转
    function controlPage4(val){
        var lastPageNo = $("#lastPageNo").val();
        if(parseInt(val)>lastPageNo){
            val = lastPageNo;
        }
        controlPages(val,5);
    }


    //翻页方法
    function controlPages(nowPageNo,sizePerPage){
        //设置一下   跳转到第几页

/*        $("#pageForm").attr("action","list");
        $("#selectHql").val($("#adStatus").val());
        $("#actType").val("list");
        $("#pageForm").submit();*/
        $.ajax(
            {
                type:"POST",
                url: '/clouds/users/admin/adviceInfo/list',
                data: { "pageNo": nowPageNo, "pageSize": pageSize},
                dataType: "json",
                success:function (res) {
                 /*   location.href = "activitySubList?activityId=" + amid+" ";*/
                 /*   location.href = "/clouds/users/admin/findNoQuery/AdviceInfo";*/

                    $("#totalCount").val(res.data[2]);
                    $("#lastPageNo").val(res.data[1]);
                    $("#nowPageNo1").val(nowPageNo);
                    $("#pageSize").val(pageSize);
                }

            }
        );
    }

    //可以在本页面定制翻页部分的 参数   和  设置方法
    function pageVarChange(){
        //对每页条数最大值的定制
        if( $("#sizePerPage")!=null&&$("#sizePerPage").val()!=""&&$("#sizePerPage").val()>50 ){
            $("#sizePerPage").val(50);
        }

    }

</script>