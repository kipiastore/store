<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная Одессы | Call центр</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home-min.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<a name="top" id="top"></a>
<form method="get" action="<c:url value="/search"/>" id="mainForm">
    <%@include file="/WEB-INF/views/portal/components/topBar.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/header.jspf"%>
    <div style="margin-top: -30px;">
        <div style="padding: 1px 0 0px 0;">
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb">Поиск</h2>
                <div class="rptShort">
                </div>
            </div>
        </div>
    </div>
    <k:forEach var="item" items="${companies}">
        <div class="rua-l-wrapper2" style="border-color: hsla(0,${ packageToColor.get(item.companyPackageId) }%,66%,1)">
            <div class="companyMainInfo">
                <a data-id="${item.id}" href="../company/${item.id}">
                    <h3>${item.name}</h3>
                </a>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;${item.description}</span>
                <span class="companyAmount">
                    <k:if test="${not empty item.costOf}">
                        Стоимость: <b>${item.costOf}</b>
                    </k:if>
                </span>
            </div>
            <div class="AddressList">
                <k:forEach var="addresItem" items="${companyToCompanyAddress.get(item.id)}">
                    <div class="address">
                        <span class="addressInfo">${addresItem.address}</span>&nbsp;
                        <span>${addresItem.phones}</span>&nbsp;
                        <span>${addresItem.information}</span>
                    </div>
                </k:forEach>
            </div>
        </div>
    </k:forEach>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>