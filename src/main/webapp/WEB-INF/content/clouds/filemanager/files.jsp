<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
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
<jsp:include page="../utils/account.jsp"></jsp:include>

<div class="container">
    <h1> 我的文件</h1>
    <hr/>

    <h3>所有文件 <a href="/clouds/filemanager/uploadfile/${loginUser.username}" type="button" class="btn btn-primary btn-sm">添加文件</a></h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty fileList}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>files表为空，请<a href="/clouds/filemanager/uploadfile/${loginId}" type="button" class="btn btn-primary btn-sm">添加</a>
        </div>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty fileList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>文件名</th>
              <%--  <th>描述</th>--%>
                <th>文件大小</th>
                <th>上传日期</th>
                <th>状态</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${fileList}" var="file">
                <tr>
                    <td>${file.id}</td>
                    <td>${file.fileName}</td>
                 <%--   <td>${file.description}</td>--%>
                    <td>${file.size}</td>
                    <td><fmt:formatDate value="${file.createtime }" pattern="yyyy-MM-dd"/></td>
                    <td>${file.status}</td>
                    <td>
                        <a href="/clouds/filemanager/files/show/${file.id}" type="button" class="btn btn-sm btn-success">详情</a>
                        <a href="/clouds/filemanager/files/update/${loginUser.username}/${file.id}" type="button" class="btn btn-sm btn-warning">修改</a>
                        <a href="/clouds/filemanager/files/delete/${loginUser.username}/${file.id}" type="button" class="btn btn-sm btn-danger">删除</a>
                        <a href="/clouds/filemanager/files/download/${file.id}/${file.fileName}" type="button" class="btn btns-sm btn-info">下载</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <div>
    <!-- 翻页部分 -->
        <form:form id="pageForm" name="pageForm" modelAttribute="pageAttribute" >
            <table width="100%">
                <tr>
                    <td style="float:right;color:#047072">
                        <a href="#" onclick="controlPage(1,'${pager.getSizePerPage()}');" style="color:#037170;text-decoration: none;">首页</a>&nbsp;
                        <a href="#" onclick="controlPage('${pager.getBeforPageNo()}','${pager.getSizePerPage()}');" style="color:#037170;text-decoration: none;">上一页</a>&nbsp;
                        <a href="#" onclick="controlPage('${pager.getNextPageNo()}','${pager.getSizePerPage()}');" style="color:#037170;text-decoration: none;">下一页</a>&nbsp;
                        <a href="#" onclick="controlPage('${pager.getLastPageNo()}','${pager.getSizePerPage()}');" style="color:#037170;text-decoration: none;">末页</a>&nbsp;
                        第<input id="nowPageNo" name="nowPageNo" type="text" onchange="controlPageNo(this,'${pager.getNowPageNo()}','${pager.getSizePerPage()}');" value="${pager.getNowPageNo()}" size="1"/>页&nbsp;
                        每页<input id="sizePerPage" name="sizePerPage" type="text" onchange="controlPageSize(this,'${pager.getNowPageNo()}','${pager.getSizePerPage()}');" value="${pager.getSizePerPage()}" size="1"/>条数&nbsp;
                        总${pager.getLastPageNo()}页&nbsp;
                        总${pager.getTotalCount()}条
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>


</body>


<script>

    //翻页方法
    function controlPage(varNowPageNo,varSizePerPage){
        //设置一下   跳转到第几页
        $("#nowPageNo").val(varNowPageNo);
        //调用页面的自定义翻页部分的变量方法
        pageVarChange();

        //form提交
        $("#pageForm").attr("action","/clouds/filemanager/files/${loginUser.username}");
        $("#actType").val("list");
        $("#pageForm").submit();
    }


    //第几页的输入修正
    //判断是否为正整数isPositiveNum
    function controlPageNo(e,varBefore,varSizePerPage){
        if( e == null || e.value.trim()=="" || isPositiveNum(e.value.trim()) ){
            e.value = varBefore;
            return false;
        }
        controlPage(e.value.trim(),varSizePerPage);
    }

    //每页多少条数的输入修正
    //判断是否为正整数isPositiveNum
    function controlPageSize(e,varNowPageNo,varBefore){
        if( e == null || e.value.trim()=="" || isPositiveNum(e.value.trim()) ){
            e.value = varBefore;
            return false;
        }
        controlPage(varNowPageNo,e.value.trim());
    }

    //判断是否为正整数
    function isPositiveNum(s){
        var re = /^[0-9]*[1-9][0-9]*$/ ;
        return !re.test(s);
    }

    //可以在本页面定制翻页部分的 参数   和  设置方法
    function pageVarChange(){
        //对每页条数最大值的定制
        if( $("#sizePerPage")!=null&&$("#sizePerPage").val()!=""&&$("#sizePerPage").val()>50 ){
            $("#sizePerPage").val(50);
        }

    }

</script>

</html>