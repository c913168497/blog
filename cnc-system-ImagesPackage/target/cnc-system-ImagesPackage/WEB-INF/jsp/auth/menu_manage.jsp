<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-05-11
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/css/common.css">
  <style>
    .topbar{
      width: 100%;
      height: 60px;
    }
    .topbarleft{
      width: 50%;
      height: 60px;
      float: left;
     }
    .topbarright{
      width: 48%;
      height: 60px;
      float: left;
    }
    body{
      overflow-x: hidden;
      overflow-y: hidden;
      background: url("${pageContext.request.contextPath}/static/sys/images/bg-body.jpg");
    }

  </style>
  <jsp:include page="../head.jsp"/>
</head>
<body style="padding: 0px;margin: 0px;">
  <div class="topbar">
      <div class="topbarleft">
        <shiro:hasPermission name="/menu/list">
          <div  class="sys_button" id="refresh"><span class="sys_icon sys_icon_refresh">&nbsp;</span>重新加载</div>
        </shiro:hasPermission>
        <shiro:hasPermission name="/menu/add">
          <div  class="sys_button" id="newMenu"><span class="sys_icon sys_icon_add">&nbsp;</span>新建菜单</div>
        </shiro:hasPermission>
        <shiro:hasPermission name="/menu/delete">
          <div  class="sys_button" id="deleteMenu"><span class="sys_icon sys_icon_delete">&nbsp;</span>删除菜单</div>
        </shiro:hasPermission>
        <shiro:hasPermission name="/menu/update">
          <div  class="sys_button" id="modifyMenu"><span class="sys_icon sys_icon_modify">&nbsp;</span>修改菜单</div>
        </shiro:hasPermission>
        </div>
      <div class="topbarright">
        <shiro:hasPermission name="/menu/list">
        <div  class="sys_button" id="refresh_fun"><span class="sys_icon sys_icon_refresh">&nbsp;</span>重新加载</div>
        </shiro:hasPermission>
        <shiro:hasPermission name="/menu/add">
        <div  class="sys_button" id="newFun"><span class="sys_icon sys_icon_add">&nbsp;</span>新建功能</div>
        </shiro:hasPermission>
        <shiro:hasPermission name="/menu/delete">
        <div  class="sys_button" id="deleteFun"><span class="sys_icon sys_icon_delete">&nbsp;</span>删除功能</div>
        </shiro:hasPermission>
        <shiro:hasPermission name="/menu/update">
        <div  class="sys_button" id="modifyFun"><span class="sys_icon sys_icon_modify">&nbsp;</span>修改功能</div>
        </shiro:hasPermission>
      </div>
  </div>

  <!-- 展示菜单表格 -->
  <div id="menutable" style="width: 50%;float: left;height: 800px;">
    <div id="dataMenuList"></div>

    <div id="addMenuDialog" class="easyui-dialog" data-options="iconCls:'user_add',closed:'true',modal:true" style="width:420px">
      <form  id="addMenuForm" class="easyui-form" data-options="novalidate:true"  method="post" >
        <input type="hidden" id="type" name= "type">
        <table class="fromTab" cellpadding="5"  >
          <tr >
            <td>上级菜单：</td>
            <td >
              <input  id="superMenu" style="width: 205px" type="text" name = "pid" class="easyui-textbox"   data-options="required:true"   />
            </td>
          </tr>
          <tr>
            <td>菜单名称：</td>
            <td>
              <input id="name" style="width: 205px"  type="text" name="name" class="easyui-textbox"  data-options="required:true"  />
            </td>
          </tr>
          <tr>
            <td>菜单超链接：</td>
            <td>
              <input id="url" style="width: 205px" type="text" name="url"    class="easyui-textbox"  data-options="required:true" />
            </td>
          </tr>
          <tr>
            <td>唯一标识：</td>
            <td>
              <input id="sn" style="width: 205px"  type="text" name="sn"    class="easyui-textbox"  data-options="required:true" />
            </td>
          </tr>
          <tr>
            <td>访问权限符：</td>
            <td>
              <input id="permission" style="width: 205px" type="text" name="permission" class="easyui-textbox" data-options="required:true" />
            </td>
          </tr>
          <tr>
            <td>显示菜单：</td>
            <td>
              <input id="display" style="width: 205px" type="text"  name="display" class="easyui-combobox"
                     data-options="valueField:'value',textField:'text',editable:false,width:50,panelHeight:'auto',
                   data:[{text:'是',value:1},{text:'否',value:0}]" />
            </td>
          </tr>
        </table>

      </form>
      <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" id="saveMenuBtn" class="easyui-linkbutton"  style="width: 100px;" >确定</a>
      </div>
    </div>
  </div>


  <!--展示功能表格 -->
  <div id="funtable" style="width: 50%;float: left;">
    <div id="dataFunList" ></div>
  </div>


</body>
<jsp:include page="../footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/sys/js/auth/menu_manage.js"></script>
<script>
  function changeFrameHeight(){
    $("#menutable").height(($(window).height())*0.88)
    $("#funtable").height(($(window).height())*0.88)
  }
  changeFrameHeight();
  window.onresize=function(){
    changeFrameHeight();
  }
</script>

</html>
