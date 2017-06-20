<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-04-20
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
  // 重定向到新地址
  response.setStatus(response.SC_MOVED_TEMPORARILY);
  response.setHeader("Location", "index");
%>
</body>
</html>
