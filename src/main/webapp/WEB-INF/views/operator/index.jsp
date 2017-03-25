<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Справочная служба</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/operator.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
</head>
<body>
<%@include file="/WEB-INF/views/operator/components/topbar.jspf"%>
<h3 class="h3Logo"><a>Основные разделы</a></h3>
<div class="menu">
    <k:forEach var="partitionItem" items="${model.partitionItems}">
        <div class="content">
            <a href="${prefix}partition/${partitionItem.partitionId}">${partitionItem.partitionName}</a>
        </div>
    </k:forEach>
    <div style="height: 20px; width: 100%;float: left;"></div>
</div>
<%@include file="/WEB-INF/views/operator/components/searchwindow.jspf" %>
<%@include file="/WEB-INF/views/operator/components/bottombar.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/operator.js" />"></script>
</body>
</html>
