<%--
  Created by IntelliJ IDEA.
  User: Pan
  Date: 16/12/3
  Time: 下午7:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>菜品信息首页</title>
  </head>
  <body>
    <h1>首页</h1><hr>
    <a href="${pageContext.request.contextPath}/addFoods.jsp">添加菜品</a><br>
    <a href="${pageContext.request.contextPath}/servlet/ListFoodsServlet">查看菜品</a><br>
    <a href="${pageContext.request.contextPath}/servlet/PageFoodsServlet?thispage=1">分页查看菜品</a><br>
    <a href="${pageContext.request.contextPath}/servlet/ListOrderServlet">查看订单</a>
  </body>
</html>
