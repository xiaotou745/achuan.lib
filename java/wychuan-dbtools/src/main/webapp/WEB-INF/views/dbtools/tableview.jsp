<%@page import="com.wychuan.code.conf.DbSetting"%>
<%@page import="com.wychuan.dbtools.common.ModelViewUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wychuan.dbtools.model.TableViewModel" %>
<%
	TableViewModel model = ModelViewUtils.getData(request);
%>
<input type="hidden" value="<%=model.getTableHasEditOper() %>" id="tableHasEditOper" />
<input type="hidden" value="<%=model.getTableHasGenerateCodeOper() %>" id="tableHasGenerateCodeOper" />
<div class="row">
	<div class="col-md-3">
		<div class="well">
			<form id="dbSelector">
				<legend>配置条件</legend>
				<input type="hidden" data-type="1" id="viewType" />
				<fieldset>
					<div class="form-group">
                        <label class="control-label" for="dbServer">选择服务器</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="icon-search"></i></span>
                            
                            <select id="dbServer" name="dbServer" class="form-control">
                            	<% for(DbSetting dbserver : model.getDbSettings()) {%>
                            		<option data-serverid="<%=dbserver.getId() %>"><%= dbserver.getDbServer()%>
                            	<%}%>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label" for="txtDbName">选择数据库</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="icon-search"></i></span>
                            <input type="text" id="txtDbName" name="dbName" class="form-control" value="<%=model.getCurrentDbName() %>" data-provide="typeahead" data-items="100" data-source="<%=model.getDbNameSource()%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="txtTableName" class="control-label">选择数据表</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="icon-search"></i></span>
                            <input type="text" id="txtTableName" class="form-control" data-provide="typeahead" data-items="50" data-source="<%=model.getDbTableSource() %>" />
                        </div>
                        <a id="returnTableList" href="javascript:;">返回上一步</a>
                    </div>
				</fieldset>
				
			</form>
		</div>
	</div>
	<div class="col-md-9" id="contents">
		<jsp:include page="./_tableviewcontents.jsp"></jsp:include>
	</div>
</div>