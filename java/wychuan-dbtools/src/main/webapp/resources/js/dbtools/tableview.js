$(function () {
    //服务器列表选择更新
    $("#dbServer").on("change", function () {
    	var serverid=$(this).find("option:checked").data("serverid");
    	$("#txtDbName").val("");
    	$("#txtTableName").val("");
        refresh(1, "", "",serverid);
    });

    $("#txtDbName").on("keydown", function (evt) {
        evt = (evt) ? evt : ((window.event) ? window.event : "");
        var keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
        if (keyCode != 13) {
            return;
        }
        var dbName = $("#txtDbName").val();
        if (dbName == "") {
            $("#txtDbName").focus();
            return;
        }
        var dbServer = $("#currentDbServerId").val();
        $("#txtTableName").val("");
        refresh(2, dbName, "",dbServer);
    });
    $(document).delegate("td[name='dbname'] a", "click", function () {
        var dbServerid = $("#currentDbServerId").val();
        var dbname = $(this).text();
        $("#txtDbName").val(dbname);
        refresh(2, dbname, "" ,dbServerid);
    });

    $(document).delegate("td.J_tableName a", "click", function () {
        var dbServerid = $("#currentDbServerId").val();
        var dbName = $("#currentDbName").val();
        var tableName = $(this).text();
        $("#txtTableName").val(tableName);
        refresh(3, dbName, tableName, dbServerid);
    });
    $("#txtTableName").on("keydown", function (evt) {
        evt = (evt) ? evt : ((window.event) ? window.event : "");
        var keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
        if (keyCode != 13) {
            return;
        }
        var dbTableName = $("#txtTableName").val();
        if (dbTableName == "") {
            $("#txtTableName").focus();
            return;
        }
        var dbServer = $("#currentDbServerId").val();
        var dbName = $("#currentDbName").val();
        refresh(3, dbName, dbTableName, dbServer);
    });

    //返回上一步
    $("#returnTableList").bind("click", function () {
        var viewType = $("#currentViewType").val();
        var dbServerid = $("#currentDbServerId").val();
        if (viewType == 2) {
            refresh(1,  "", "", dbServerid);
            $("#txtDbName").val("");
        } else if (viewType == 3) {
            refresh(2,  $("#currentDbName").val(), "", dbServerid);
            $("#txtTableName").val("");
        }
    });

    //对话框弹框事件
    $('#modalDescEdit').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var viewType = $("#currentViewType").val();
        if (viewType == 2) {
            var tableName = button.parent().data("table");
            var desc = button.parent().data("desc");
            $("#lblTableName").val(tableName);
            $("#lblColumnName").val("");
            $("#txtDesc").val(desc);
            $("#txtDesc").focus();
        } else if (viewType == 3) {
            var tableName2 = $("#currentTableName").val();
            var columnName = button.parent().data("col");
            var desc2 = button.parent().data("desc");

            $("#lblTableName").val(tableName2);
            $("#lblColumnName").val(columnName);
            $("#txtDesc").val(desc2);
            $("#txtDesc").focus();
        }
    });

    //保存描述信息
    $("#btnSaveDesc").bind("click", function () {
        var dbServer = $("#currentDbServerId").val();
        var dbName = $("#currentDbName").val();
        var tableName = $("#lblTableName").val();
        var columnName = $("#lblColumnName").val();
        var desc = $("#txtDesc").val();
        
        var params = {
        		dbServerId:dbServer,
        		dbName:dbName,
        		tableName:tableName,
        		columnName:columnName,
        		desc:desc
        };

        $.post(
            window.basePath + "/dbtools/modifydesc",
            params,
            function (data) {
                if (data) {
                    refresh($("#currentViewType").val(), dbName, tableName, dbServer);
                    $("#modalDescEdit").modal("hide");
                }
            }
        );
    });
});

//刷新
function refresh(viewType,  dbName, dbTable, dbServerId) {
    var tableHasEditOper = $("#tableHasEditOper").val();
    var tableHasGenerateCodeOper = $("#tableHasGenerateCodeOper").val();
    var viewParams={
    		dbServerId:dbServerId,
    		viewType:viewType,
    		dbName:dbName,
    		dbTable:dbTable,
    		hasEdit:tableHasEditOper,
    		hasGenerateCode:tableHasGenerateCodeOper
    };
    $.blockUI();
    $.ajax({
    	url:window.basePath + "/dbtools/refresh",
    	type:"post",
    	data:viewParams,
    	success:function(resp){
    		$("#contents").html(resp);
            $("#codeResult").html("");
    	},
    	complete:function(){
    		$.unblockUI();
    	}
    })
}