<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная Одессы | Call центр</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<a name="top" id="top"></a>
<form method="get" action="<c:url value="/search"/>" id="mainForm">
    <%@include file="/WEB-INF/views/portal/components/topBar.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/header.jspf"%>
    <div style="margin-top: -30px;">
        <div style="padding: 1px 0 30px 0;">
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb">test</h2>
                <div class="rptShort">
                    <k:forEach var="item" items="${model.partitionItem.subPartitionItems}">
                        <p>
                            <a data-id="${item.subPartitionId}" href="../subPartition/${item.subPartitionId}">${item.subPartitionName}</a>
                            <span class="rua-p-c-red">${item.companyCount}</span>
                        </p>
                    </k:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="bestResources">
        <div style="opacity: 1; width: 100%; text-align:center; margin-top: 30px; margin-bottom: 30px;">
            <h3>Редакция рекомендует</h3>
        </div>
    </div>
    <div style="opacity: 0; width: 100%; height: 20px;"></div>
    <k:forEach var="item" items="${model.companyHiPrior}">
        <div class="rua-l-wrapper2" style="border-color: hsla(0,${item.colorPoint}%,66%,1)">
            <a data-id="${item.companyId}" href="../company/${item.companyId}">
                <h3>${item.companyName}</h3>
            </a>
            <span>${item.companyInformation}</span>
        </div>
    </k:forEach>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>