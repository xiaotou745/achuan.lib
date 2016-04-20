<%@page import="com.wychuan.dbtools.common.ModelViewUtils"%>
<%@page import="com.wychuan.dbtools.model.TableViewModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
TableViewModel model = ModelViewUtils.getData(request);
%>
<input type="hidden" value="<%=model.getCurrentViewType() %>" id="currentViewType" />
<input type="hidden" value="<%=model.getCurrentServerId()%>" id="currentDbServerId"/>
<%-- <input type="hidden" value="<%=model.getCurrentServer() %>" id="currentDbServer" /> --%>
<input type="hidden" value="<%=model.getCurrentDbName() %>" id="currentDbName" />
<input type="hidden" value="<%=model.getCurrentTableName() %>" id="currentTableName" />

<%=model.getHtmlResult().toString()%>