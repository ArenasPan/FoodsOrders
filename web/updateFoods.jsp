<%--
  Created by IntelliJ IDEA.
  User: Pan
  Date: 16/12/4
  Time: 下午7:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>修改菜品信息</title>
</head>
<body>
<div align="center">
    <form action="${pageContext.request.contextPath}/servlet/UpdateFoodsServlet" method="POST" enctype="multipart/form-data">
        <input name="id" type="hidden" value="${foods.id}"/>
        <table border="1">
            <tr>
                <td>菜名</td>
                <td><input name="name" type="text" value="${foods.name}" readonly="readonly" style="background: silver"/>
                </td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input name="price" type="text" value="${foods.price}"/></td>
            </tr>
            <tr>
                <td>菜品图片</td>
                <td>
                    <input type="file" name="uploadFile"/>
                </td>
                <%--<td><input name="cellphone" type="text" value="${foods.cellphone}"/></td>--%>
            </tr>
            <tr>
                <td>菜品分类</td>
                <td>
                    <select name="category">
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
                </td>
            </tr>
            <tr>
                <td>描述信息</td>
                <td><textarea name="description" rows="6" cols="40">${foods.description}</textarea></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" value="修改菜品"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
