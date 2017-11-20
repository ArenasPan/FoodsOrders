<%--
  Created by IntelliJ IDEA.
  User: Pan
  Date: 16/12/3
  Time: 下午10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>菜品列表</title>
    <script type="text/javascript">
        function checkAll(checkAll) {
            var otherC = document.getElementsByName("delId");
            for (var i = 0; i < otherC.length; i++) {
                otherC[i].checked = checkAll.checked;
            }
        }
    </script>
</head>
<body>
<h1>菜品列表</h1>
<hr>
<div align="center">
    <form action="${pageContext.request.contextPath}/servlet/FindFoodsByCondServlet" method="post">
        菜名:<input type="text" name="name" value="${param.name}"/>
        菜品类型:<select name="category">
                <option value="酒水"
                        <c:if test="${foods.category=='酒水'}">
                            selected="selected"
                        </c:if>
                >酒水
                </option>
                <option value="凉菜"
                        <c:if test="${foods.category=='凉菜'}">
                            selected="selected"
                        </c:if>
                >凉菜
                </option>
                <option value="炒菜"
                        <c:if test="${foods.category=='炒菜'}">
                            selected="selected"
                        </c:if>
                >炒菜
                </option>
                <option value="火锅"
                        <c:if test="${foods.category=='火锅'}">
                            selected="selected"
                        </c:if>
                >火锅
                </option>
                <option value="干锅"
                        <c:if test="${foods.category=='干锅'}">
                            selected="selected"
                        </c:if>
                >干锅
                </option>
                <option value="主食"
                        <c:if test="${foods.category=='主食'}">
                            selected="selected"
                        </c:if>
                >主食
                </option>
                <option value="靓汤"
                        <c:if test="${foods.category=='靓汤'}">
                            selected="selected"
                        </c:if>
                >靓汤
                </option>
            </select>
        <input type="submit" value="查询菜品"/>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/BatchDelServlet" method="post">
        <table border="1">
            <tr>
                <th>
                    <input type="checkbox" onclick="checkAll(this)"/>全选
                </th>
                <th>菜名</th>
                <th>价格</th>
                <th>菜品图片</th>
                <th>菜品分类</th>
                <th>描述信息</th>
                <th>修改</th>
                <th>删除</th>
            </tr>

            <c:forEach items="${requestScope.list}" var="foods">
                <tr>
                    <td style="text-align: center"><input type="checkbox" name="delId" value="${foods.id}"/></td>
                    <td style="text-align: center">${foods.name}</td>
                    <td style="text-align: center">${foods.price}</td>
                    <td style="text-align: center"><img src="${pageContext.request.contextPath}/${foods.imageUrl}" width="160" height="100"></td>
                    <td style="text-align: center">${foods.category}</td>
                    <td style="text-align: center">${foods.description}</td>
                    <td style="text-align: center"><a
                            href="${pageContext.request.contextPath}/servlet/FoodsInfoServlet?id=${foods.id}">修改</a></td>
                    <td style="text-align: center"><a
                            href="${pageContext.request.contextPath}/servlet/DelFoodsServlet?id=${foods.id}">删除</a></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="批量删除"/>
    </form>
</div>
</body>
</html>
