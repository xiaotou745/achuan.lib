<%@page import="com.wychuan.dbtools.common.UrlGet"%>
<%@page import="com.wychuan.spring.PropertyUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%
	String basePath = PropertyUtils.getProperty("dbtools.basepath");
%>

<header class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<%=basePath%>">Db Tools</a>
		<div class="navbar-collapse nav-collapse collapse navbar-header">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<%=UrlGet.get("/home") %>">Home<span class="sr-only">(current)</span></a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle js-activated">Db Tools <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<%=UrlGet.get("/table")%>">表结构查看</a></li>
						<li><a href="<%=UrlGet.get("/code") %>">代码生成</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">个人设置</li>
						<li><a href="#">代码模板配置</a></li>
						<li><a href="#">命名空间配置</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">I Have a
						Submenu <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="#">Menu Item</a></li>
						<li><a tabindex="-1" href="#">Bootstrap 3 Does Not Support Submenus</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">Account <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="#">My Account</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" href="#">Change Email</a></li>
						<li><a tabindex="-1" href="#">Change Password</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" href="#">Logout</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">Contact <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="#">Contact</a></li>
						<li><a tabindex="-1" href="#">Contact My Mom</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" href="#">Contact Your Mom</a></li>
						<li><a tabindex="-1" href="#">Contact the President</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- .nav-collapse -->
	</div>
	<!-- .container -->
</header>
