<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.wychuan.dbtools.common.UrlGet" %>

<jsp:include page="./_tableviewJs.jsp"></jsp:include>

<div id="generateCodeSettings" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="codeGenerateModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="codeGenerateModalLabel">代码生成参数</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="codeType" value="1" />
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-sm-offset-1" for="codeTableName">所选择表</label>
                        <div class="col-sm-8">
                            <input type="text" class="disabled form-control" id="codeTableName" disabled="disabled" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-sm-offset-1">语言</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="ddlCodeLanguage">
                                <option value="1">C#</option>
                                <option value="0">Java</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-sm-offset-1">架构选择</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="ddlCodeLayer">
                            </select>
                            <p class="help-inline"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-sm-offset-1">DAO实现</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="ddlDaoStyle">
                            </select>
                            <p class="help-inline"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-sm-offset-1" for="ddlCallStyle">对象创建</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="ddlCallStyle">
                            </select>
                            <p class="help-inline"></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-lg btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-lg btn-primary" id="btnGenerateCode">代码生成</button>
            </div>
        </div>
    </div>
</div>
<link href="<%=UrlGet.get("css/highlighter/shCore.css")%>" rel="stylesheet" />
<link href="<%=UrlGet.get("css/highlighter/shCoreDefault.css")%>" rel="stylesheet" />
<script type="text/javascript" src="<%=UrlGet.get("js/highlighter/shCore.js")%>"></script>
<script type="text/javascript" src="<%=UrlGet.get("js/highlighter/shBrushCSharp.js")%>"></script>
<script type="text/javascript" src="<%=UrlGet.get("js/highlighter/shBrushJava.js")%>"></script>
<script type="text/javascript" src="<%=UrlGet.get("js/highlighter/shBrushXml.js")%>"></script>
<script type="text/javascript">
$(function() {
    $("#ddlCallStyle, #ddlCodeLayer, #ddlDaoStyle").change(function() {
        var title = $(this).find("option:selected").attr("title");
        $(this).next("p").text(title);
    });
    $("#ddlCodeLayer").change();
    $("#ddlCallStyle").change();
    $("#ddlDaoStyle").change();

    $('#generateCodeSettings').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var tableName = button.parent().data("table");
        $("#codeTableName").val(tableName);
    });

    $("#btnGenerateCode").bind("click", function() {
        var dbServer = $("#currentDbServerId").val();
        var dbName = $("#currentDbName").val();
        var tableName = $("#codeTableName").val();
        
        var params = {
        		serverId:dbServer,
        		dbName:dbName,
        		tableName:tableName,
        		language:$("#ddlCodeLanguage").val()
        };
        
        $.ajax({
        	url:window.basePath +"/dbtools/generate",
        	type:"post",
        	data:params,
        	success:function(resp){
        		$("#codeResult").html(resp);
                $("#generateCodeSettings").modal("hide");
        		SyntaxHighlighter.highlight();
        	}
        });
    });
  	SyntaxHighlighter.all();
})
</script>