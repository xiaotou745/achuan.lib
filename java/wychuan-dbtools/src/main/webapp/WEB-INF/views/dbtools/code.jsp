<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wychuan.dbtools.common.ModelViewUtils" %>
<%@ page import="com.wychuan.dbtools.model.TableViewModel" %>
<%@ page import="com.wychuan.code.conf.DbSetting" %>
<%@page import="com.wychuan.dbtools.common.UrlGet" %>
<%
	TableViewModel model = ModelViewUtils.getData(request);
%>
<style type="text/css">
    .tabbable {
  *zoom: 1;
}

.tabbable:before,
.tabbable:after {
  display: table;
  line-height: 0;
  content: "";
}

.tabbable:after {
  clear: both;
}

.tab-content {
  overflow: auto;
}

.tabs-below > .nav-tabs,
.tabs-right > .nav-tabs,
.tabs-left > .nav-tabs {
  border-bottom: 0;
}

.tabs-left > .nav-tabs > li,
.tabs-right > .nav-tabs > li {
  float: none;
}

.tabs-left > .nav-tabs > li > a,
.tabs-right > .nav-tabs > li > a {
  min-width: 74px;
  margin-right: 0;
  margin-bottom: 3px;
}

.tabs-left > .nav-tabs {
  float: left;
  margin-right: 19px;
  border-right: 1px solid #ddd;
}

.tabs-left > .nav-tabs > li > a {
  margin-right: -1px;
  -webkit-border-radius: 4px 0 0 4px;
     -moz-border-radius: 4px 0 0 4px;
          border-radius: 4px 0 0 4px;
}

.tabs-left > .nav-tabs > li > a:hover,
.tabs-left > .nav-tabs > li > a:focus {
  border-color: #eeeeee #dddddd #eeeeee #eeeeee;
}

.tabs-left > .nav-tabs .active > a,
.tabs-left > .nav-tabs .active > a:hover,
.tabs-left > .nav-tabs .active > a:focus {
  border-color: #ddd transparent #ddd #ddd;
  *border-right-color: #ffffff;
}
</style>
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
	<div class="col-md-9">
		<ul class="nav nav-tabs">
			<li class="active">
				<a href="#contents" data-toggle="tab" aria-expanded="true">详情</a>
			</li>
			<li>
				<a href="#codeResult" data-toggle="tab">代码</a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="contents">
				<jsp:include page="./_tableviewcontents.jsp"></jsp:include>
			</div>
			<div class="tab-pane" id="codeResult">
				<jsp:include page="./_coderesult.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
