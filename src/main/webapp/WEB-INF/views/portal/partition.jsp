<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная Одессы | ${model.partitionItem.partitionName}</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home-min2.css" />"/>
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
                <h2 class="headline centered mtmb">${model.partitionItem.partitionName}</h2>
                <div class="rptShort">
                    <div style="float: left;width: 465px;">
                        <k:forEach var="item" items="${model.partitionItem.subPartitionItems2}">
                            <p>
                                <a data-id="${item.subPartitionId}" href="../subPartition/${item.subPartitionId}">${item.subPartitionName}</a>
                                <span class="rua-p-c-red">${item.companyCount}</span>
                            </p>
                        </k:forEach>
                    </div>
                    <div style="float: right;width: 455px;">
                        <k:forEach var="item" items="${model.partitionItem.subPartitionItems1}">
                            <p>
                                <a data-id="${item.subPartitionId}" href="../subPartition/${item.subPartitionId}">${item.subPartitionName}</a>
                                <span class="rua-p-c-red">${item.companyCount}</span>
                            </p>
                        </k:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <k:if test="${not empty model.companyHiPrior}">
        <div class="bestResources">
            <div style="opacity: 1; width: 100%; text-align:center; margin-top: 30px; margin-bottom: 30px;">
                <h2 class="headline centered mtmb">Редакция рекомендует</h2>
            </div>
        </div>
        <div style="opacity: 0; width: 100%; height: 20px;"></div>
        <k:forEach var="item" items="${model.companyHiPrior}">
            <div class="rua-l-wrapper2" style="border-color: hsla(0,${item.colorPoint}%,66%,1)">
                <div class="companyMainInfo">
                    <a data-id="${item.companyId}" href="../company/${item.companyId}">
                        <h3>${item.companyName}</h3>
                    </a>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;${item.companyInformation}</span>
                    <span class="companyAmount"><k:if test="${not empty item.costOf}">Стоимость: <b>${item.costOf}</b></k:if></span>
                </div>
                <div class="AddressList">
                    <k:set var="count" value="0" scope="page" />
                    <k:forEach var="addresItem" items="${model.companyToCompanyAddress.get(item.companyId)}">
                        <k:if test="${count == 0}">
                            <div class="address">
                                <span class="addressInfo">${addresItem.address}&nbsp;</span>
                                <span>${addresItem.phones}&nbsp;</span>
                                <span>${addresItem.information}</span>
                            </div>
                        </k:if>
                        <k:if test="${count > 0}">
                            <div class="hiddenAdr-btn">
                                <p><a class="btn btn-primary" data-id="${item.companyId}">Филиалы</a></p>
                            </div>
                            <div class="address hiddenAdr address-${item.companyId}">
                                <span class="addressInfo">${addresItem.address}&nbsp;</span>
                                <span>${addresItem.phones}&nbsp;</span>
                                <span>${addresItem.information}</span>
                            </div>
                        </k:if>
                        <k:set var="count" value="${count + 1}" scope="page"/>
                    </k:forEach>
                </div>
            </div>
        </k:forEach>
    </k:if>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>