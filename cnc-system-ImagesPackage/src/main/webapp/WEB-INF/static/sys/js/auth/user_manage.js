var userManage = function() {
    var userManage = this;
    this.URL = {
        listUser: '/user/list',
        addUser: '/user/add',
        updateUser: '/user/update',
        deleteUser: function (id) {
            return '/user/delete/'+id;
        },
        userResource: function (id){
            return '/user/userResource/'+id;
        },
        userRoleResource: function (id){
            return '/user/userRoleResource/'+id;
        },
        getUserRole: function(id){
            return "/user/getUserRole/"+id;
        },
        setPermission: "/user/setUserRolePermission"
    }
    this.saveOrUpdate = 0; //默认添加
    this.userId;

    $("#userRoleResource").datagrid({
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
            {field:'name',title:'角色名',width:200},
            {field:'status',title:'状态',width:200,formatter:function(value){
                if(value == 1)return "可用";else return "不可用";}
            }
        ]]
    });
    //简化分页栏信息
    $("#userRoleResource").datagrid('getPager').pagination({
        pageSize:50,
        pageList:[20,30,50,100],
        layout:['list','sep','first','prev','sep','manual','sep','next','last'],
        showRefresh: false
    });
    $("#userResource").tree({
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

    $("#pexpandAllBtn").click(function(){$("#permissionList").tree("expandAll")});
    $("#pcollapseAllBtn").click(function(){$("#permissionList").tree("collapseAll")});
    this.initGrid = function (){
        $("#dataUserList").datagrid({
            url: userManage.URL.listUser,
            singleSelect: true,
            checkOnSelect: true,
            fitColumns: true,
            fit: true,
            border: false,
            singleSelect:true,
            rownumbers: true,
            pagination: true,
            pageSize: 20,
            pageList: [20, 30, 50, 100],
            lines: true,
            onClickRow: function() {//双击加载右边的功能菜单框
                var row = $('#dataUserList').datagrid('getSelected');
                var options = $('#userRoleResource').datagrid('options');
                options.url = userManage.URL.userRoleResource(row.id);
                $('#userRoleResource').datagrid("load");
                var options2 = $("#userResource").tree('options');
                options2.url = userManage.URL.userResource(row.id);
                $('#userResource').tree(options2);
            },
            columns:[[
                {field: 'id', hidden: true},
                {field:'name',title:'用户名称',width:200},
                {field:'account',title:'用户账号',width:200},
                {field:'is_lock',title:'状态',width:200,formatter:function(value){
                    if(value == 1)return "可用";else return "不可用";}
                }
            ]]
        });
    }



    this.initQuery = function(){
        $("#newUser").click(function (){
            $("#addUserForm").form("clear");
            userManage.saveOrUpdate = 0;

            $("#account").textbox({readonly:false});
            $("#is_lock").combobox("setValue",1);
            $("#addUserDialog").dialog("setTitle","用户添加");
            $("#addUserDialog").dialog("open");
        });
        $("#deleteUser").click(function (){
            var row = $("#dataUserList").datagrid("getSelected");
            if(!row){
                $.messager.alert("提示","请选择表格中的一项！");
                return;
            }
            $.messager.confirm("提示","确认删除该用户?",function(r){
                if(r){
                    $.post(userManage.URL.deleteUser(row.id),function(data){
                        $("#dataUserList").datagrid("reload")
                    })
                }
            })
        });
        $("#modifyUser").click(function (){
            $("#addUserForm").form("clear");
            var row = $("#dataUserList").datagrid("getSelected");
            if(!row){
                $.messager.alert("提示","请选择表格中的一项！");
                return;
            }
            $("#account").textbox({readonly:true});
            $("#name").textbox("setValue",row.name);
            $("#account").textbox("setValue",row.account);
            $("#is_lock").combobox("setValue",row.is_lock);
            userManage.saveOrUpdate = 1;
            $("#addUserDialog").dialog("setTitle","角色修改");
            $("#addUserDialog").dialog("open");
        });
        $("#saveUserBtn").click(function (){
            var url = userManage.URL.addUser;
            if(userManage.saveOrUpdate == 1){
                url = userManage.URL.updateUser;
            }
            $("#addUserForm").form("submit",{
                url:url,
                onSubmit:function(param){
                    if(userManage.saveOrUpdate == 1){
                        param.id = $("#dataUserList").datagrid("getSelected").id;
                    }
                    return $(this).form('enableValidation').form('validate');
                },
                success:function(data){
                    var dataobj = eval('(' + data + ')'); // change the JSON string to javascript object
                    if(dataobj.type == 'success'){
                        $('#addUserDialog').dialog('close');
                        $("#dataUserList").datagrid("reload")
                    }else if(dataobj.type == 'error'){
                        $.messager.alert("提示",dataobj.text);
                    }else {
                        $.messager.alert("提示","未知错误");
                        $('#addUserDialog').dialog('close');
                    }
                }
            })
        });
        //############打开权限设置对话框######
        $("#setUser").click(function() {
            var roleRed = $('#dataUserList').datagrid('getSelected');
            if(!roleRed){
                $.messager.alert('提示', '请先选中表格中的一项操作！');
                return;
            }
            userManage.userId = roleRed.id;
            $("#permissionList").tree({
                method:'post',
                url: userManage.URL.getUserRole(roleRed.id),
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
            $.post(userManage.URL.setPermission,{userList:ids,userId:userManage.userId},function(data){
                if(data.type == 'success'){
                    $("#setPermissionDialog").dialog("close");
                    $('#userRoleResource').datagrid("reload");
                    $("#userResource").tree("reload");
                }else {
                    $("#setPermissionDialog").dialog("close");
                    $.messager.alert("提示","未知错误!")
                }
            })
        });
        $("#reloadBtn").click(function (){
            $('#userRoleResource').datagrid("reload");
        })

    }

    this.init = function (){
        userManage.initGrid();
        userManage.initQuery();
    }

}
$(function (){
    new userManage().init();
})