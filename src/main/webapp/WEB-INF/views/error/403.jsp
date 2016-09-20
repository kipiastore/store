<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>HTTP Status 403 - Access is denied</h1>
<h2>${msg}</h2>
<a href="<c:url value="/login"/>">Sign in</a>
</body>
</html>