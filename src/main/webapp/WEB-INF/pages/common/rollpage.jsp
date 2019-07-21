<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>
<blockquote>
	<div class="layui-inline" style="float: left">
		共 ${param.totalCount} 条记录 ${param.currentPageNo}/${param.totalPageCount}页
	</div>
	<div class="layui-inline" style="float: right">
		<c:if test="${param.currentPageNo > 1}">
			<a class="layui-btn layui-btn-xs " href="javascript:page_nav(document.forms[0],1);">首页</a>
			<a  class="layui-btn layui-btn-xs" href="javascript:page_nav(document.forms[0],${param.currentPageNo-1});">上一页</a>
		</c:if>
		<c:if test="${param.currentPageNo < param.totalPageCount }">
			<a  class="layui-btn layui-btn-xs" href="javascript:page_nav(document.forms[0],${param.currentPageNo+1 });">下一页</a>
			<a  class="layui-btn layui-btn-xs" href="javascript:page_nav(document.forms[0],${param.totalPageCount });">最后一页</a>
		</c:if>
	</div>
</blockquote>
</body>
<script type="text/javascript" src="static/js/rollpage.js"></script>
</html>