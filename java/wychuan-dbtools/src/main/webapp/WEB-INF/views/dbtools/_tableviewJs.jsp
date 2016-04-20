<%@page import="com.wychuan.dbtools.common.UrlGet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="modalDescEdit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="accountModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="accountModalLabel">编辑描述信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2">表名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="disabled form-control" id="lblTableName" disabled="disabled" placeholder="表名称" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2">列名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="disabled form-control" id="lblColumnName" disabled="disabled" placeholder="列名称" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="txtDesc">描述</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="txtDesc"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnSaveDesc" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=UrlGet.getContextPath()%>/js/dbtools/tableview.js"></script>
