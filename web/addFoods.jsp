<%--
  Created by IntelliJ IDEA.
  User: Pan
  Date: 16/12/3
  Time: 下午8:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加菜品</title>
</head>
<body>
<div align="center">
    <form action="${pageContext.request.contextPath}/servlet/AddFoodsServlet" method="POST" enctype="multipart/form-data">
        <table border="1">
            <tr>
                <td>菜名</td>
                <td><input name="name" type="text"/></td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input name="price" type="text"/></td>
            </tr>
            <tr>
                <td>菜品图片</td>
                <td>
                    <input type="file" name="uploadFile"/>
                </td>
            </tr>
            <tr>
                <td>菜品分类</td>
                <td>
                    <select name="category">
                        <option value="酒水">酒水</option>
                        <option value="凉菜">凉菜</option>
                        <option value="炒菜">炒菜</option>
                        <option value="火锅">火锅</option>
                        <option value="干锅">干锅</option>
                        <option value="主食">主食</option>
                        <option value="靓汤">靓汤</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>描述信息</td>
                <td><textarea name="description" rows="6" cols="40"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" value="添加菜品"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
