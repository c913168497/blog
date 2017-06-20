<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-04-20
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/css/style.css" media="all" />
  <style>
    .msg{
      width: 250px;
      height: 20px;
      position: absolute;
      z-index: 999;
      color: red;
      text-align: left;
    }
  </style>
</head>


<body class="login">
<section>
  <h1><strong>Welcome</strong>! </h1>
  <form method="post" action="login">
    <input type="text" id="username" name="username" placeholder="Email" />
    <input placeholder="Password" id="password" name="password" type="password" />
    <button class="blue">Login</button>
  </form>
  <p><span id="msg" class="msg">${emsg}</span></p>
</section>
<script src="${pageContext.request.contextPath}/static/jquery/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
  $(function(){
    $('.login button').click(function(e){
        if($("#username").val()==""){
          $("#msg").html("email必填！")
          return false;
        }
        if($("#password").val()==""){
          $("#msg").html("password必填！")
          return false;
        }
        $(this).addClass("loading");
        e.preventDefault;
    });
    $('input').each(function() {

      var default_value = this.value;

      $(this).focus(function(){
        if(this.value == default_value) {
          this.value = '';
        }
      });
      $(this).blur(function(){
        if(this.value == '') {
          this.value = default_value;
        }
      });
    });
  });
</script>
</body>
</html>
