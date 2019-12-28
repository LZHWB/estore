<%--
  Created by IntelliJ IDEA.
  User: lenovo1
  Date: 2019/12/24
  Time: 22:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单消息</title>
</head>
<body>
<center>
    <%--服务器回送注册失败的错误信息--%>
    ${requestScope["regist.message"]}
        <a href='http://localhost:8084'>首页</a>
</center>
</body>
</html>
