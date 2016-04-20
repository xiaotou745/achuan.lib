<%@page import="com.wychuan.dbtools.common.UrlGet"%>
<%@page import="com.wychuan.spring.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath = PropertyUtils.getProperty("dbtools.basepath");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
	
	<title><tiles:getAsString name="title" /></title>

	<meta name="description" content="数据库工具.">
	<meta name="author" content="wychuan">
	<!-- main css -->
	<link rel="stylesheet" href="<%=basePath %>/css/bs/bootstrap.min.css">
	<link rel="stylesheet" href="<%=UrlGet.get("/font-awesome/css/font-awesome.min.css") %>">
	<!--[if IE 7]>
	<link rel="stylesheet" href="<%=UrlGet.get("/font-awesome/css//font-awesome-ie7.min.css") %>">
	<![endif]-->

	<!-- main js -->
	<script type="text/javascript" src="<%=basePath %>/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/bootstrap-hover-dropdown.min.js"></script>
	<script type="text/javascript" src="<%=UrlGet.get("/js/jquery.blockUI.js") %>"></script>	
	<style type="text/css">
		body {
			padding-top: 70px;
			min-height: 410px
		}
		
		.tab-content p {
			padding: 10px 0;
		}
	</style>
	<script type="text/javascript">
		window.basePath = "<%=basePath%>";
	</script>
	<!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
	<%-- <tiles:insertAttribute name="headerJsCss" ignore="true"></tiles:insertAttribute> --%>
</head>
	
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<tiles:insertAttribute name="body"></tiles:insertAttribute>
			</div>
		</div>
	</div>
	
	<tiles:insertAttribute name="footerJsCss" ignore="true"></tiles:insertAttribute>
	
	<script type="text/javascript">
	// very simple to use!
	$(document).ready(function() {
		$('.js-activated').dropdownHover().dropdown();
	});
	</script>
</body>
</html>