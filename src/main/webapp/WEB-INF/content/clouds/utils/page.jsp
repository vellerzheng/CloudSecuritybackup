<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/page.js"></script>
  <form:form id="pageForm" name="pageForm" modelAttribute="pageAttribute" >
	  <input id="selectHql" name="selectHql" type="hidden" value="${pager.getSelectHql()}"/>
	  <input id="whereHql" name="whereHql" type="hidden" value="${pager.getWhereHql()}"/>
	  <input id="orderHql" name="orderHql" type="hidden" value="${pager.getOrderHql()}"/>
	  <input id="whereString" name="whereString" type="hidden" value="${pager.getWhereString()}"/>
	  <input id="orderKey" name="orderKey" type="hidden" value="${pager.getOrderKey()}"/>
	  <input id="ascDesc" name="ascDesc" type="hidden" value="${pager.getAscDesc()}"/>
	  <input id="idList" name="idList" type="hidden"  />
	  <input id="actType" name="actType" type="hidden"  />
	  <input id="exportStartPage" name="exportStartPage" type="hidden"/>
	  <input id="exportEndPage" name="exportEndPage" type="hidden"/>
	  <input id="formsupid" name="id" type="hidden"/>
	  <input id="formactivityId" name="activityId" type="hidden"/>
	  <input id="formadstatus" name="adStatus" type="hidden"/>
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