<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo1
  Date: 2019/12/19
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询显示商品信息</title>
    <script type="text/javascript">
        function sel(){
            var msg=document.getElementById("msg").value;
            if(msg==null||msg==""){
                alert("请输入你要查询的内容!");
            }else {
                document.getElementById("s").submit();
            }
        }
    </script>
</head>
<body>
<div align="center">
<form action="${pageContext.request.contextPath}/ProductSimpleServlet" method="post" id="s">
    <select name="field">
        <option disabled="disabled">请选择查询条件</option>
        <option value="name">按商品名称查询</option>
        <option value="description">按照商品描述查询</option>
    </select>
    <input type="text" name="msg" id="msg">
    <input type="button" value="查询" onclick="sel()">
</form>
</div>
<br>
<form action="${pageContext.request.contextPath}/ProductSimpleServlet" method="post" id="f">
<table border="1" align="center" width="85%">
    <tr>
        <td>商品编号</td>
        <td>商品名称</td>
        <td>商品价格</td>
        <td>商品数量</td>
        <td>商品分类</td>
        <td>商品颜色</td>
        <td>商品图片</td>
        <td>商品描述</td>
        <td>加入购物车</td>
    </tr>

    <c:forEach items="${requestScope.pro}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.pnum}</td>
            <td>${p.c3code}</td>
            <td>${p.color}</td>
            <td height="160" width="300"><img alt="图片不存在" width="300" height="160" src="/upload/${p.imgurl}"></td>
            <td>${p.description}</td>
            <td>
                <a href="${pageContext.request.contextPath}/AddProductToCartServlet?pid=${p.id}">加入购物车</a>
            </td>
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>