<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
    <title>Админка</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/mainAdmin.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<body>
<%@include file="/WEB-INF/views/mainAdmin/components/logout.jspf"%>
<%@include file="/WEB-INF/views/mainAdmin/components/header.jspf"%>
</body>
</html>
