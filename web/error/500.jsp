<%--
  Created by IntelliJ IDEA.
  User: Pan
  Date: 16/12/3
  Time: 下午8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>发生错误啦</title>
</head>
<body>
    服务器出错了，亲~~~~  出错原因:${pageContext.exception.message}
</body>
</html>
