<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Справочная служба</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/operator.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
</head>
<body>
<%@include file="/WEB-INF/views/operator/components/topbar.jspf"%>
<h3 class="h3Logo">
    <a href="${prefix}">Основные разделы</a>
    <span>&nbsp;&gt;&nbsp;</span>
    <a>Результаты поиска</a>
</h3>
<div class="menu">
    <div class="empDiv2"><h3>Поиск по слову:</h3></div>
    <k:forEach var="item" items="${model.companyItemsByKeyword}">
        <div class="content">
            <a style="color: hsla(0,${item.colorPoint}%,66%,1);" href="${prefix}company/A-${item.companyId}">${item.companyName}</a>
        </div>
    </k:forEach>
    <div class="empDiv2"><h3>Поиск по фирме:</h3></div>
    <k:forEach var="item" items="${model.companyItemsByCompany}">
        <div class="content">
            <a style="color: hsla(0,${item.colorPoint}%,66%,1);" href="${prefix}company/A-${item.companyId}">${item.companyName}</a>
        </div>
    </k:forEach>
    <div class="empDiv2"><h3>Поиск по адресу:</h3></div>
    <k:forEach var="item" items="${model.companyItemsByAddress}">
        <div class="content">
            <a style="color: hsla(0,${item.colorPoint}%,66%,1);" href="${prefix}company/A-${item.companyId}">${item.companyName}</a>
        </div>
    </k:forEach>
</div>
<%@include file="/WEB-INF/views/operator/components/searchwindow.jspf"%>
<%@include file="/WEB-INF/views/operator/components/bottombar.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/operator.js" />"></script>
</body>
</html>