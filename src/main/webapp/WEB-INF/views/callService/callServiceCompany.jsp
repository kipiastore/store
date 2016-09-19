<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Справочная служба</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/callService.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
</head>
<body>
<%@include file="/WEB-INF/views/callService/components/topBar.jspf"%>
<h3 class="h3Logo">Компания: ${callServiceCompanyModel.companyName}</h3>
<div class="menu">

</div>
<%@include file="/WEB-INF/views/callService/components/searchWindow.jspf"%>
<%@include file="/WEB-INF/views/callService/components/bottomBar.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/callService.js" />"></script>
</body>
</html>
