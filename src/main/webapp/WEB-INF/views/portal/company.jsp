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
        <div style="padding: 1px 0 0px 0;">
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb">${company.name}</h2>
            </div>
        </div>
    </div>
    <div class="rua-l-wrapper2" style="border-color: hsla(0,${color}%,66%,1)">
        <div class="companyMainInfo">
            <h3>${company.name}</h3>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;${company.description}</span>
            <span class="companyAmount">
                <k:if test="${not empty company.costOf}">
                    Стоимость: <b>${company.costOf}</b>
                </k:if>
            </span>
        </div>
        <div class="AddressList">
            <k:forEach var="addresItem" items="${addresses}">
                <div class="address">
                    <span class="addressInfo">${addresItem.address}</span>&nbsp;
                    <span>${addresItem.phones}</span>&nbsp;
                    <span>${addresItem.information}</span>
                </div>
            </k:forEach>
        </div>
    </div>

    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>