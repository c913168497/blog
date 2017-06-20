/**
 *
 */
var menuManage = function() {
    var menuMange = this;
    this.URL = {
        listMenuTree:'/menu/list/tree',
        listMenuGrid:'/menu/list',
        addMenu:'/menu/add',
        updateMenu:'/menu/update',
        deleteMenu:function(menuId){
            return '/menu/delete/'+menuId;
        },
        listFunGrid:function(id){
            return '/menu/fun/list/'+id;
        }
    };
    this.menuAddOrUpdate = 0; //(菜单: 1修改，0新建)
    this.menuOrFun = 0;  //(0菜单 1 功能)
    this.formJson = "";
    this.menuChioce = 1;
    this.funChioce = 2;
;

    /** 初始化表格*/
    this.initGrid = function () {
        $('#dataMenuList').treegrid({
            url:menuMange.URL.listMenuGrid,
            singleSelect:true,
            checkOnSelect:true,
            fitColumns:true,
            fit:true,
            border:false,
            toolbar:'#tt',
            idField:'id',
            treeField:'name',
            lines:true,
            onClickRow: function(node) {//双击加载右边的功能菜单框
                var options = $('#dataFunList').datagrid('options');
                options.url = menuMange.URL.listFunGrid(node.id);
                $('#dataFunList').datagrid("load");
            },
            columns:[[
                {field:'id', hidden:true },
                {field:'_parentId',hidden:true },
                {field:'name',title:'菜单名称',width:200 },
                {field:'permission',title:'权限字符串',width:100 ,hidden:true},
                {field:'url',title:'超链接',width:200 },
                {field:'sn',title:'唯一标识符',width:100 },
                {field:'display',title:'是否显示',width:50,align:'left' ,
                    formatter:function(value,row,index){
                        if(value == 1){
                            return "<div class='icon icon-true'>&nbsp;</div>";
                        }else{
                            return "<span class='icon icon-false'>&nbsp;</span>";
                        }
                    }
                },
            ]]
        });

        //树结构功能
        $("#expandAllBtn").click(function(){ $('#dataMenuList').treegrid('expandAll');});
        $("#collapseAllBtn").click(function(){ $('#dataMenuList').treegrid('collapseAll');});
        $("#refresh").click(function(){ $('#dataMenuList').treegrid('reload');});
    }
    //菜单功能
    //=============================弹出框的树结构============================================//
    $("#superMenu").combotree({
        method:'post',
        url: menuMange.URL.listMenuTree,
        parentField:"pId",
        textFiled:"name",
        editable:true,
        lines:true
    });
    //功能菜单表格的初始化
    this.initFunGrid = function() {
        $('#dataFunList').datagrid({
            url: '',
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
            columns: [[
                {field: 'id', hidden: true},
                {field: 'name', title: '功能名称', width: 100},
                {field: 'permission', title: '权限字符串', width: 100, hidden: true},
                {field: 'url', title: '功能的超链接', width: 200},
                {field: 'sn', title: '唯一标识符', width: 100},
                {
                    field: 'display', title: '是否可用', width: 100, align: 'left',
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return "<div class='icon icon-true'>&nbsp;</div>";
                        } else {
                            return "<span class='icon icon-false'>&nbsp;</span>";
                        }
                    }
                }
            ]]
        });
    }
    this.initQuery = function(){
        $("#newMenu").click(function(){     //新建菜单
            var menuRed = $('#dataMenuList').treegrid('getSelected');
            if(!menuRed){
                $.messager.alert('提示',"请选择表格中的一项进行操作!");
                return;
            }
            $('#addMenuForm').form('clear');
            //$(".am-gallery-item").remove();
            if(menuRed){
                $('#superMenu').combotree('setValue',menuRed.id);
            }
            $("#display").combobox('select',1);//默认选中是

            $('#type').val(menuMange.menuChioce);
            console.log("类型："+$("#type").val())
            menuMange.menuAddOrUpdate = 0;
            menuMange.menuOrFun = 0;
            $("#addMenuDialog").dialog('setTitle','添加菜单');
            $('#addMenuDialog').dialog("open");
        });

        $("#deleteMenu").click(function(){  //删除菜单
            var menuRed = $('#dataMenuList').treegrid('getSelected');
            if(!menuRed){
                $.messager.alert("提示","请选择表格中的一项进行操作!");
                return;
            }
            $.messager.confirm('提示', '确认删除该菜单？', function(r){
                    if(r){
                        $.post(menuMange.URL.deleteMenu(menuRed.id),function(data){
                            var datastr = JSON.stringify(data);
                            var dataobj = eval('(' + datastr + ')'); // change the JSON string to javascript object
                            if(dataobj.type == 'success'){
                                menuMange.menuOrFun = 0;
                                $('#dataMenuList').treegrid('reload');
                            }else{
                                $.messager.alert("提示","未知错误!");
                            }
                        })
                    }
            });

        });

        $("#modifyMenu").click(function(){ //修改菜单
            var rows = $('#dataMenuList').treegrid('getSelected');
            $('#addMenuForm').form('clear');
            $(".am-gallery-item").remove();
            if(!rows){
                $.messager.alert('提示', '请选中表格中的一项操作！');
                return;
            }
            if(rows._parentId == null){
                $.messager.alert('提示', '根目录不允许修改！');
                return;
            }
            $('#superMenu').combotree('setValue',rows._parentId);
            $('#name').textbox('setValue',rows.name);
            $('#url').textbox('setValue',rows.url);
            $('#sn').textbox('setValue',rows.sn);
            $('#permission').textbox('setValue',rows.permission);
            $("#display").combobox('select',rows.display);//默认选中是
            $('#type').val(menuMange.menuChioce);
            menuMange.formJson = JSON.stringify($("#addMenuForm").serializeArray()); //用于验证表单内容是否修改
            menuMange.menuAddOrUpdate = 1;                                          //设置修改1
            menuMange.menuOrFun = 0;
            $("#addMenuDialog").dialog('setTitle','修改菜单');
            $('#addMenuDialog').dialog("open");
        });
        $("#saveMenuBtn").click(function(){
            console.log("类型："+$("#type").val())

            var url = menuMange.URL.addMenu;            //默认0为添加操作
            if(menuMange.menuAddOrUpdate == 1){
                var formJson2 = JSON.stringify($("#addMenuForm").serializeArray());
                console.log("上传数据"+formJson2);
                if(formJson2 == menuMange.formJson){
                    $.messager.alert("提示","未进行任何修改");
                    return;
                }
                url = menuMange.URL.updateMenu;
            }
            $("#addMenuForm").form("submit",{
                url:url,
                onSubmit:function(param){
                    if(menuMange.menuAddOrUpdate == 1){
                        if( menuMange.menuOrFun == 0){
                            param.id = $("#dataMenuList").datagrid("getSelected").id;
                        }else if(menuMange.menuOrFun == 1){
                            param.id = $("#dataFunList").datagrid("getSelected").id;
                        }
                    }
                    return $(this).form('enableValidation').form('validate');
                },
                success:function(data){
                    var datastr = JSON.stringify(data);
                    datastr = datastr.replace(/\"/g,"");
                    datastr = datastr.replace(/\\/g,"\"");
                    console.log("菜单"+datastr);
                    var dataobj = eval('(' + datastr + ')'); // change the JSON string to javascript object
                    if(dataobj.type == 'success'){
                        if(menuMange.menuOrFun == 1){
                            var options = $('#dataFunList').datagrid('options');
                            options.url = menuMange.URL.listFunGrid($("#dataMenuList").datagrid("getSelected").id);
                            $('#dataFunList').datagrid("load");
                            $('#addMenuDialog').dialog('close');
                        }else if(menuMange.menuOrFun == 0){
                            $('#dataMenuList').treegrid('reload');
                            $('#addMenuDialog').dialog('close');
                        }
                    }else if(dataobj.type == 'error'){
                        $.messager.alert("提示",dataobj.text);
                        $('#addMenuDialog').dialog('close');
                    }
                }
            });
        })

        $("#refresh_fun").click(function (){
            var rows = $('#dataMenuList').treegrid('getSelected');
            $('#addMenuForm').form('clear');
            if(!rows){
                $.messager.alert('提示', '请选中菜单中的一项操作！');
                return;
            }
            var options = $('#dataFunList').datagrid('options');
            options.url = menuMange.URL.listFunGrid(rows.id);
            $('#dataFunList').datagrid("load");
        });
        $("#newFun").click(function (){
            menuMange.menuOrFun = 1;                            //功能
            menuMange.menuAddOrUpdate = 0;
            var rows = $('#dataMenuList').treegrid('getSelected');
            $('#addMenuForm').form('clear');
            if(!rows){$.messager.alert('提示', '请选中菜单中的一项操作！'); return; }
            $("#display").combobox('select',1);                 //默认选中是
            $('#type').val(menuMange.funChioce);
            $('#superMenu').textbox('setValue',rows.id);
            $('#superMenu').textbox('setText',rows.name);
            $("#addMenuDialog").dialog('setTitle','添加功能');
            $('#addMenuDialog').dialog('open');

        })
        $("#deleteFun").click(function (){
            var menuRed = $('#dataFunList').treegrid('getSelected');
            if(!menuRed){
                $.messager.alert("提示","请选择表格中的一项进行操作!");
                return;
            }
            $.messager.confirm('提示', '确认删除该功能？', function(r){
                if(r){
                    $.post(menuMange.URL.deleteMenu(menuRed.id),function(data){
                        var datastr = JSON.stringify(data);
                        var dataobj = eval('(' + datastr + ')'); // change the JSON string to javascript object
                        if(dataobj.type == 'success'){
                            menuMange.menuOrFun = 1;
                            $("#refresh_fun").click();
                        }else{
                            $.messager.alert("提示","未知错误!");
                        }
                    })
                }
            });
        })
        $("#modifyFun").click(function (){
            menuMange.menuOrFun = 1;
            menuMange.menuAddOrUpdate = 1;
            var rows = $('#dataFunList').treegrid('getSelected');
            var rows2 = $('#dataMenuList').treegrid('getSelected');
            $('#addMenuForm').form('clear');
            $(".am-gallery-item").remove();
            if(!rows){
                $.messager.alert('提示', '请选中表格中的一项操作！');
                return;
            }
            $('#type').val(menuMange.funChioce);
            $('#superMenu').textbox('setValue',rows2.id);
            $('#superMenu').textbox('setText',rows2.name);
            $('#name').textbox('setValue',rows.name);
            $('#url').textbox('setValue',rows.url);
            $('#sn').textbox('setValue',rows.sn);
            $('#permission').textbox('setValue',rows.permission);
            $("#display").combobox('select',rows.display);//默认选中是
            $("#addMenuDialog").dialog('setTitle','修改功能');
            $('#addMenuDialog').dialog("open");
        })
    }
    this.init = function(){
        menuMange.initGrid();
        menuMange.initFunGrid();
        menuMange.initQuery();
    }
}
$(function () {
    new menuManage().init();
})