<%--
  Created by IntelliJ IDEA.
  User: cnc
  Date: 2017-06-12
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>角色管理</title>
  <style>
    .topbar{
      width: 100%;
      height: 60px;
    }
  </style>
</head>
<body>
<jsp:include page="../head.jsp"/>
<div class="topbar">
  <%--<shiro:hasPermission name="/menu/list">--%>
  <div  class="sys_button" id="refresh"><span class="sys_icon sys_icon_refresh">&nbsp;</span>重新加载</div>
  <%--</shiro:hasPermission>--%>
  <%--<shiro:hasPermission name="/menu/add">--%>
  <div  class="sys_button" id="newUser"><span class="sys_icon sys_icon_add">&nbsp;</span>新建用户</div>
  <%--</shiro:hasPermission>--%>
  <%--<shiro:hasPermission name="/menu/delete">--%>
  <div  class="sys_button" id="deleteUser"><span class="sys_icon sys_icon_delete">&nbsp;</span>删除用户</div>
  <%--</shiro:hasPermission>--%>
  <%--<shiro:hasPermission name="/menu/update">--%>
  <div  class="sys_button" id="modifyUser"><span class="sys_icon sys_icon_modify">&nbsp;</span>修改用户</div>
  <%--</shiro:hasPermission>--%>
  <div  class="sys_button" id="setUser"><span class="sys_icon sys_icon_modify">&nbsp;</span>设置角色</div>
</div>

<!--   -->
<div id="usertable" style="width: 60%;float: left;margin-top:2px;">
  <div id="dataUserList" ></div>
</div>
<div id="roletable" style="width: 40%;float: right;">
  <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false,tools:'#tab-tools'">
    <div title="角色" >
      <div id="userRoleResource"></div>
    </div>
    <div title="权限">
      <div id="tt" class="datagrid-toolbar" style="padding-top: 5px;">
        <a href="javascript:void(0)" id="expandAllBtn"  class="easyui-linkbutton" iconCls="folder" plain="true" >全部展开</a>
        <a href="javascript:void(0)" id="collapseAllBtn"  class="easyui-linkbutton" iconCls="folder_go" plain="true" >全部折叠</a>
        <a href="javascript:void(0)" id="reloadBtn"  class="easyui-linkbutton" iconCls="icon-reload"	plain="true" >重新加载</a>
      </div>
      <div id="userResource"></div>
    </div>
  </div>
</div>
<!-- 设置权限框 -->
<div  id="setPermissionDialog" class="easyui-dialog"  data-options="iconCls:'user_add',closed:'true',modal:true,title:'设置权限'" style="width:330px;height: 80%">
  <div id="pp" class="datagrid-toolbar" style="padding-top: 5px;">
    <a href="javascript:void(0)" id="pexpandAllBtn"  class="easyui-linkbutton" iconCls="folder" plain="true" >全部展开</a>
    <a href="javascript:void(0)" id="pcollapseAllBtn"  class="easyui-linkbutton" iconCls="folder_go" plain="true" >全部折叠</a>
  </div>
  <div style="text-align:left;padding:5px">
    <div id="permissionList"></div>
    <div id="dlg-buttons">
      <a href="javascript:void(0)" id="savePerBtn" class="easyui-linkbutton" >保存</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#setPermissionDialog').dialog('close')">关闭</a>
    </div>
  </div>
</div>
<!-- 展示角色表格 -->
<div id="addUserDialog" class="easyui-dialog" data-options="iconCls:'user_add',closed:'true',modal:true" style="width:420px">
  <form  id="addUserForm" class="easyui-form" data-options="novalidate:true"  method="post" >
    <input type="hidden" id="type" name= "type">
    <table class="fromTab" cellpadding="5"  >
      <tr>
        <td>名称：</td>
        <td>
          <input id="name" style="width: 205px"  type="text" name="name" class="easyui-textbox"  data-options="required:true"  />
        </td>
      </tr>
      <tr>
        <td>账号：</td>
        <td>
          <input id="account" style="width: 205px"  type="text" name="account" class="easyui-textbox"  data-options="required:true"  />
        </td>
      </tr>
      <tr>
        <td>密码：</td>
        <td>
          <input id="password" style="width: 205px"  type="text" name="password" class="easyui-textbox"  data-options="required:true"  />
        </td>
      </tr>
      <tr>
        <td>状态：</td>
        <td>
          <input id="is_lock" style="width: 205px" type="text"  name="is_lock" class="easyui-combobox"
                 data-options="valueField:'value',textField:'text',editable:false,width:50,panelHeight:'auto',
                   data:[{text:'可用',value:'1',selected:true},{text:'不可用',value:'0'}]" />
        </td>
      </tr>
    </table>
  </form>
  <div style="text-align:center;padding:5px">
    <a href="javascript:void(0)" id="saveUserBtn" class="easyui-linkbutton"  style="width: 100px;" >确定</a>
  </div>
</div>
</body>
<jsp:include page="../footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/sys/js/auth/user_manage.js"></script>
<script>
  function changeFrameHeight(){
    $("#usertable").height(($(window).height())*0.88)
    $("#roletable").height(($(window).height())*0.88)
  }
  changeFrameHeight();
  window.onresize=function(){
    changeFrameHeight();
  }
</script>
</html>
