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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index_login.css">
  <script src="${pageContext.request.contextPath}/static/js/modernizr-1.7.min.js"></script>
</head>

<body>
<div id="container">
  <div id="main" role="main">
    <form method="post" action="login">
      <fieldset>
        <label for="username">用户名:<span class="ico"><img src="${pageContext.request.contextPath}/static/images/user.png" alt="Username Icon" border="0"></span></label>
        <input type="text" name="username" id="username" required="" autofocus="">
        <label for="password">密码:<span class="ico"><img src="${pageContext.request.contextPath}/static/images/pass.png" alt="Password Icon" border="0"></span></label>
        <input type="password" name="password" id="password" required="">
      </fieldset>
      <fieldset>
        <span class="password"><a href="#">忘记密码?</a></span>
        <button type="submit">>>登录</button>
      </fieldset>
    </form>
  </div>
</div>
</body>
</html>
