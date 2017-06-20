var roleManage = function() {
    var roleManage = this;
    this.URL = {
        listRole: '/role/list',
        addRole: '/role/add',
        updateRole: '/role/update',
        deleteRole: function (id) {
            return '/role/delete/'+id;
        },
        roleResource: function (id){
            return '/role/roleResource/'+id;
        },
        roleUserResource: function (id){
            return '/role/roleUserResource/'+id;
        },
        setGrant: function(id){
            return "/role/set/permission/list/"+id;
        },
        setPermission: "/role/set/permission"
    }
    this.saveOrUpdate = 0; //默认添加
    this.roleId;

    $("#roleUserResource").datagrid({
        singleSelect: true,
        checkOnSelect: true,
        fitColumns: true,
        fit: true,
        border: false,
        rownumbers: true,
        pagination: true,
        pageSize: 20,
        pageList: [20, 30, 50, 100],
        lines: true,
        columns:[[
            {field: 'id', hidden: true},
            {field:'account',title:'角色名',width:200},
            {field:'status',title:'状态',width:200,formatter:function(value){
                if(value == 0)return "可用";else return "不可用";}
            }
        ]]
    });
    //简化分页栏信息
    $("#roleUserResource").datagrid('getPager').pagination({
        pageSize:50,
        pageList:[20,30,50,100],
        layout:['list','sep','first','prev','sep','manual','sep','next','last'],
        showRefresh: false
    });
    $("#roleResource").tree({
        method:'post',
        parentField:"pId",
        textFiled:"name",
        idFiled:"id",
        lines:true,
        formatter:function(node){
            if(node.status == 0){
                return "<span style='color:#ccc'>"+node.name+"(已禁用)</span>";
            }
            return node.name;
        },
        onClick:function(node){
            if(!$(this).tree('isLeaf',node.target)){
                $(this).tree('toggle', node.target);
            }
        }
    });
    this.initGrid = function (){
        $("#dataRoleList").datagrid({
            url: roleManage.URL.listRole,
            singleSelect: true,
            checkOnSelect: true,
            fitColumns: true,
            fit: true,
            border: false,
            rownumbers: true,
            pagination: true,
            pageSize: 20,
            pageList: [20, 30, 50, 100],
            lines: true,
            onClickRow: function() {//双击加载右边的功能菜单框
                var row = $('#dataRoleList').datagrid('getSelected');
                var options = $('#roleUserResource').datagrid('options');
                options.url = roleManage.URL.roleUserResource(row.id);
                $('#roleUserResource').datagrid("load");

                var options2 = $("#roleResource").tree('options');
                options2.url = roleManage.URL.roleResource(row.id);
                $('#roleResource').tree(options2);

            },
            columns:[[
                {field: 'id', hidden: true},
                {field:'name',title:'角色名',width:200},
                {field:'sn',title:'角色唯一标识',width:200},
                {field:'status',title:'状态',width:200,formatter:function(value){
                    if(value == 1)return "可用";else return "不可用";}
                }
            ]]
        });
    }



    this.initQuery = function(){
        $("#newRole").click(function (){
            roleManage.saveOrUpdate = 0;
            $("#addRoleDialog").dialog("setTitle","角色添加");
            $("#addRoleDialog").dialog("open");
        });
        $("#deleteRole").click(function (){
            var row = $("#dataRoleList").datagrid("getSelected");
            if(!row){
                $.messager.alert("提示","请选择表格中的一项！");
                return;
            }
            $.messager.confirm("提示","确认删除该角色?",function(r){
                if(r){
                    $.post(roleManage.URL.deleteRole(row.id),function(data){
                        $("#dataRoleList").datagrid("reload")
                    })
                }
            })
        });
        $("#modifyRole").click(function (){
            $("#addRoleForm").form("clear");

            var row = $("#dataRoleList").datagrid("getSelected");
            if(!row){
                $.messager.alert("提示","请选择表格中的一项！");
                return;
            }
            console.log(row)
            $("#name").textbox("setValue",row.name);
            $("#sn").textbox("setValue",row.sn);
            $("#status").combobox("setValue",row.status);
            roleManage.saveOrUpdate = 1;
            $("#addRoleDialog").dialog("setTitle","角色修改");
            $("#addRoleDialog").dialog("open");
        });
        $("#saveRoleBtn").click(function (){
            var url = roleManage.URL.addRole;
            if(roleManage.saveOrUpdate == 1){
               url = roleManage.URL.updateRole;
            }
            $("#addRoleForm").form("submit",{
                url:url,
                onSubmit:function(param){
                    if(roleManage.saveOrUpdate == 1){
                        param.id = $("#dataRoleList").datagrid("getSelected").id;
                    }
                    return $(this).form('enableValidation').form('validate');
                },
                success:function(data){
                    var dataobj = eval('(' + data + ')'); // change the JSON string to javascript object
                    if(dataobj.type == 'success'){
                        $('#addRoleDialog').dialog('close');
                        $("#dataRoleList").datagrid("reload")
                    }else if(dataobj.type == 'error'){
                        $.messager.alert("提示",dataobj.text);
                    }else {
                        $.messager.alert("提示","未知错误");
                        $('#addRoleDialog').dialog('close');
                    }
                }
            })
        });
 //############打开权限设置对话框######
        $("#setGrant").click(function() {
            var roleRed = $('#dataRoleList').datagrid('getSelected');
            if(!roleRed){
                $.messager.alert('提示', '请先选中表格中的一项操作！');
                return;
            }
            roleManage.roleId = roleRed.id;
            $("#permissionList").tree({
                method:'post',
                url: roleManage.URL.setGrant(roleRed.id),
                parentField:"pId",
                textFiled:"name",
                idFiled:"id",
                checkbox:true,
                cascadeCheck:false,
                lines:true,
                formatter:function(node){
                    if(node.status == 0){
                        return "<span style='color:#ccc'>"+node.name+"(已禁用)</span>";
                    }
                    return node.name;
                },
                onLoadSuccess:function(){
                    var opts = $("#permissionList").tree('options');
                    opts.cascadeCheck =true;
                },
                onClick:function(node){
                    if(!$(this).tree('isLeaf',node.target)){
                        $(this).tree('toggle', node.target);
                    }
                }
            });
            $("#pexpandAllBtn").click(function(){$("#permissionList").tree("expandAll")});
            $("#pcollapseAllBtn").click(function(){$("#permissionList").tree("collapseAll")});
            $("#setPermissionDialog").dialog("setTitle","权限设置");
            $("#setPermissionDialog").dialog("open");
        });
        $("#savePerBtn").click(function() {
            var nodes = $("#permissionList").tree("getChecked", ['checked','indeterminate']);//
            var ids = new Array();
            for(var i = 0 ; i < nodes.length; i++){
                ids.push(nodes[i].id);
            }
            $.post(roleManage.URL.setPermission,{roleList:ids,roleId:roleManage.roleId},function(data){
                if(data.type == 'success'){
                    $("#setPermissionDialog").dialog("close");
                }else {
                    $("#setPermissionDialog").dialog("close");
                    $.messager.alert("提示","未知错误!")
                }
            })
        });

    }

    this.init = function (){
        roleManage.initGrid();
        roleManage.initQuery();
    }

}
$(function (){
    new roleManage().init();
})