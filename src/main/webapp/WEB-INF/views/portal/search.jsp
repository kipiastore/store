<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%= request.getParameter("value") == null ? "" : request.getParameter("value") %>
</body>
</html>
