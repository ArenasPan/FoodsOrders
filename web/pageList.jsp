<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pan
  Date: 16/12/6
  Time: 下午9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分页显示菜品</title>
</head>
<body>
<div align="center">
    <table border="1">
        <tr>
            <th>菜名</th>
            <th>价格</th>
            <th>菜品图片</th>
            <th>菜品分类</th>
            <th>描述信息</th>
        </tr>

        <c:forEach items="${requestScope.page.list}" var="foods">
            <tr>
                <td style="text-align: center">${foods.name}</td>
                <td style="text-align: center">${foods.price}</td>
                <td style="text-align: center"><img src="${pageContext.request.contextPath}/${foods.imageUrl}" width="160" height="100"></td>
                <td style="text-align: center">${foods.category}</td>
                <td style="text-align: center">${foods.description}</td>
            </tr>
        </c:forEach>
    </table>
    当前第${page.thisPage}页，共${page.countPage}页
    <a href="${pageContext.request.contextPath}/servlet/PageCustServlet?thispage=${page.firstPage}">首页</a>
    <a href="${pageContext.request.contextPath}/servlet/PageCustServlet?thispage=${page.prePage}">上一页</a>
    <c:if test="${page.countPage<=5}">
        <c:forEach begin="1" end="${page.countPage}" step="1" var="i">
            <c:if test="${i==page.thisPage}">
                ${i}
            </c:if>
            <c:if test="${i!=page.thisPage}">
                <a href="${pageContext.request.contextPath}/servlet/PageCustServlet?thispage=${i}">${i}</a>
            </c:if>
        </c:forEach>
    </c:if>
    <c:if test="${page.countPage>5}">
        <c:choose>
            <c:when test="${page.thisPage<4}">
                <c:forEach begin="1" end="5" step="1" var="item">
                    <c:if test="${item==page.thisPage}">
                        ${item}
                    </c:if>
                    <c:if test="${item!=page.thisPage}">
                        <a href="${pageContext.request.contextPath}/servlet/PageCustServlet?thispage=${item}">${item}</a>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:when test="${page.thisPage+3>page.countPage}">
                <c:forEach begin="${page.countPage-4}" end="${page.countPage}" step="1" var="item">
                    <c:if test="${item==page.thisPage}">
                        ${item}
                    </c:if>
                    <c:if test="${item!=page.thisPage}">
                        <a href="${pageContext.request.contextPath}/servlet/PageCustServlet?thispage=${item}">${item}</a>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach begin="${page.thisPage-2}" end="${page.thisPage+2}" step="1" var="item">
                    <c:if test="${item==page.thisPage}">
                        ${item}
                    </c:if>
                    <c:if test="${item!=page.thisPage}">
                        <a href="${pageContext.request.contextPath}/servlet/PageCustServlet?thispage=${item}">${item}</a>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </c:if>
    <a href="${pageContext.request.contextPath}/servlet/PageCustServlet?thispage=${page.nextPage}">下一页</a>
    <a href="${pageContext.request.contextPath}/servlet/PageCustServlet?thispage=${page.lastPage}">尾页</a>
</div>
</body>
</html>
