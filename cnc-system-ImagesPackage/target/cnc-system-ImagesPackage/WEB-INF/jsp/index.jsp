<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-05-09
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
  <jsp:include page="head.jsp"/>
  <style>
    body{
      overflow-x: hidden;
      overflow-y: hidden;
      background: url(${pageContext.request.contextPath}/static/sys/images/bg-body.jpg);
    }
    .username{
      position: absolute;
      right:105px;
      top: 15px;
      text-align: right;
      color:white;
      font-size: 12px;
    }
    .flag{
      padding: 0px;
      margin: 0px;
    }
    .tabGuide{
      height: 20px;
      padding: 4px 15px 23px 100px;
      line-height: 20px;
      color: white;
      font-size: 12px;
    }
  </style>
</head>
<body>
  <section class="user">
    <div class="profile-img">
      <p></p>
    </div>
    <div class="username" >
      欢迎，${userInfo.account}
    </div>
    <div class="buttons">
         <a href="/logout" class="button blue" style="font-size: 12px;color: #fff">注销</a>
    </div>
  </section>
  <nav id = "nav">
    <ul id="rootMenu">
    </ul>
  </nav>
<!-- 内容tabs -->
  <div id = "center">
    <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false,tools:'#tab-tools'"></div>
  </div>

  <!-- 右键tabs菜单 -->
  <div id="mm" class="easyui-menu" style="width:150px;">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-exit">退出</div>
  </div>

  <!-- 页脚信息 -->
  <div data-options="region:'south',border:false"
       style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;font-size:12px;">
    <span id="sysVersion" style="float:left;">系统版本：V1.0</span> <span id="nowTime" style="float:left;"></span>
    <span style="font-family:arial;display: inline-block; width:70%;text-align: center">&copy; CopyRight 2017-2027, cnc.</span>
  </div>
  <jsp:include page="footer.jsp"/>
<script>
  $(function(){
    $.post("/role/list/menu/${userInfo.id}", function(data){
      var menus = transformTozTreeFormat(data);//获取所有菜单
      var rootMenu = '';
      //设置导航菜单，并设置第一个默认选中
      rootMenu = rootMenu + "<li class='tabGuide section'>导航菜单</li>";
      if(menus){

        $.each(menus[0].children,function(idx,obj){

          if(obj.display == 1){

            rootMenu  = rootMenu +"<li";
            rootMenu  = rootMenu + "><a href='javascript:'>"+obj.name+"</a>";
            rootMenu = rootMenu + "<ul class='submenu' >";
            //重点
            if(obj.children){

              $.each(obj.children, function(i, n) {

                if(n){
                  rootMenu = rootMenu + "<li><a class='flag' ref='"+n.id+"' href='#' rel='"+n.url+"' >"+n.name+"</a></li>";
                }else{
                  rootMenu = rootMenu + "<li></li>";
                }

              });
            }
            rootMenu = rootMenu + "</ul></li>"
            //end
          }
        });
        $("#rootMenu").append(rootMenu);
      }
      function changeFrameHeight(){
        $("#center").height(($(window).height())*0.88)
        $("#nav").height(($(window).height())*0.88)
      }
      changeFrameHeight();
      window.onresize=function(){
        changeFrameHeight();
      }
      $('.flag').click(function(){
        var tabTitle = $(this).text();
        var url = $(this).attr("rel");
        var id = $(this).attr("ref");
        addTab(tabTitle,url);});
    });

   function getNowTime(){
    var nowTime = new Date();
    var now = nowTime.getFullYear()+"年"+(nowTime.getMonth()+1)+"月"+nowTime.getDate()+"日"+"    "+nowTime.getHours()+":"+nowTime.getMinutes()+":"+nowTime.getSeconds();
     $("#nowTime").html(now);
   }
    getNowTime();
    setInterval(function(){getNowTime();},1000);
  });
</script>
  <script src="${pageContext.request.contextPath}/static/jquery/js/jquery.wysiwyg.js"></script>
  <script src="${pageContext.request.contextPath}/static/common/js/custom.js"></script>
  <script src="${pageContext.request.contextPath}/static/common/js/cycle.js"></script>
  <script src="${pageContext.request.contextPath}/static/jquery/js/jquery.checkbox.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/jquery/js/jquery.tablesorter.min.js"></script>
</body>
</html>
