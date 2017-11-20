<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/15
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单列表</title>
</head>
<body>
<div align="center">
    <form action="${pageContext.request.contextPath}/servlet/BatchDelOrderServlet" method="post">
        <table border="1">
            <tr>
                <th>菜名</th>
                <th>价格</th>
                <th>桌号</th>
                <th>订单号</th>
                <th>订单时间</th>
                <th>是否上菜</th>
                <th>上菜</th>
            </tr>

            <c:forEach items="${requestScope.list}" var="order">
                <tr>
                    <td style="text-align: center">${order.name}</td>
                    <td style="text-align: center">${order.price}</td>
                    <td style="text-align: center">${order.table_num}</td>
                    <td style="text-align: center">${order.order_group}</td>
                    <td style="text-align: center">${order.order_time}</td>
                    <td style="text-align: center">
                        <c:if test="${order.is_served}"><font color="green">已上菜</font></c:if>
                        <c:if test="${!order.is_served}"><font color="red">未上菜</font></c:if>
                    </td>
                    <td style="text-align: center"><a
                            href="${pageContext.request.contextPath}/servlet/UpdateOrderServlet?id=${order.id}">上菜</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="批量删除"/>
    </form>
</div>
</body>
</html>
