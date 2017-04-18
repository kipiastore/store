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
<h3 class="h3Logo">
    <a href="${prefix}">Основные разделы</a>
    <span>&nbsp;&gt;&nbsp;</span>
    <a>${model.partitionItem.partitionName}</a>
</h3>
<div class="menu">

    <div class="content">
        <k:forEach var="item" items="${model.subPartitionItems1}">
            <span style="line-height: 30px;">
                <a href="${prefix}subpartition/${item.subPartitionId}">${item.subPartitionName}</a>
            </span>
            <br/>
        </k:forEach>
    </div>
    <div class="content">
        <k:forEach var="item" items="${model.subPartitionItems2}">
            <span style="line-height: 30px;">
                <a href="${prefix}subpartition/${item.subPartitionId}">${item.subPartitionName}</a>
            </span>
            <br/>
        </k:forEach>
    </div>

    <div style="height: 50px; width: 100%;float: left;"></div>
</div>
<%@include file="/WEB-INF/views/operator/components/searchwindow.jspf"%>
<%@include file="/WEB-INF/views/operator/components/bottombar.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/operator.js" />"></script>
</body>
</html>
