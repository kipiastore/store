<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная Одессы | ${company.name}</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <style>
        .rua-l-wrapper2 {
            max-width: 960px;
            margin: 0 auto;
            border: none;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<a name="top" id="top"></a>
<form method="get" action="<c:url value="/search"/>" id="mainForm">
    <%@include file="/WEB-INF/views/portal/components/topBar.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/header.jspf"%>
    <div style="background: rgba(126, 126, 126, 0.04);">
        <div style="padding: 1px 0 1px 0;">
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb title"><a class="main-ref" href="../index">Главная страница</a> → <a class="main-ref" href="../partition/${partition.id}">${partition.name}</a> → ${company.name}</h2>
            </div>
        </div>
    </div>
    <div style="background: rgba(126, 126, 126, 0.04);">
        <div style="padding: 1px 0 1px 0;">
            <div class="rua-l-wrapper2" style="border-color: hsla(0,${color}%,66%,1)">
                <div class="companyMainInfo">
                    <k:if test="${not empty company.imageId && company.isPaid == true}">
                        <img class="position-image" src="../download?id=${company.imageId}" title="">
                    </k:if>
                    <div class="company-text-block">
                        <a >
                            <h3>${company.name}<span class="visitors">Просмотров: ${company.countCompany}</span></h3>
                        </a>
                        <span>${company.description}</span>
                        <p>
                            <a title="${company.name} - Каталог товаров Одесса" href="">Показать весь список товаров/услуг фирмы "${company.name}"</a>
                            <k:if test="${not empty company.costOf}">
                                <span class="companyAmount">Стоимость: <b>${company.costOf}</b></span>
                            </k:if>
                            <k:if test="${empty company.costOf}">
                                <span class="companyAmount">Цену уточняйте</span>
                            </k:if>
                        </p>
                    </div>
                </div>
                <div class="container-end"></div>

                <k:if test="${addresses.size() > 0}">
                    <div class="AddressList">
                        <k:set var="count" value="0" scope="page" />
                        <k:forEach var="addresItem" items="${addresses}">
                            <k:if test="${count == 0}">
                                <div class="address">
                                    <span class="addressInfo">${addresItem.address}&nbsp;</span>
                                    <span>${addresItem.phones}&nbsp;</span>
                                    <span>${addresItem.information}</span>
                                </div>
                            </k:if>
                            <k:if test="${count > 0}">
                                <div class="hiddenAdr-btn">
                                    <p><a class="btn btn-primary" data-id="${company.id}">Филиалы</a></p>
                                </div>
                                <div class="address hiddenAdr address-${company.id}">
                                    <span class="addressInfo">${addresItem.address}&nbsp;</span>
                                    <span>${addresItem.phones}&nbsp;</span>
                                    <span>${addresItem.information}</span>
                                </div>
                            </k:if>
                            <k:set var="count" value="${count + 1}" scope="page"/>
                        </k:forEach>
                    </div>
                </k:if>
            </div>
            <div class="rua-l-wrapper" style="border-color: hsla(0,${color}%,66%,1)">
                <k:set var="count2" value="0" scope="page" />
                <k:forEach var="item" items="${model.companySubpartitionContentList}">
                    <div class="rua-l-wrapper2 position-mini <k:if test="${count2 % 2 == 0}">mod-ct</k:if>">
                        <div class="companyMainInfo" >
                            <k:if test="${not empty item.imageId && company.isPaid == true}">
                                <img class="position-image" id="imageId-${item.id}" data-id="${item.imageId}" src="../download?id=${item.imageId}" title="">
                            </k:if>
                            <div class="company-text-block" style="padding: 10px 10px 10px 10px;">
                                <a >
                                    <h3>${item.subPartitionName}</h3>
                                </a>
                                <span>${item.info}</span>
                            </div>
                        </div>
                    </div>
                    <k:set var="count2" value="${count2 + 1}" scope="page"/>
                </k:forEach>
                <div class="container-end"></div>
            </div>
        </div>
    </div>
    <div class="line-separator"></div>
    <%@include file="/WEB-INF/views/portal/components/invitation.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
<script>
    setAutocomplete('<c:url value="/api/portal/resource/v1/search/"/>');
</script>
<style>
    .rua-l-wrapper2.position-mini {
        width: 472.5px;
        float: left;
    }
    .mod-ct {
        margin-right: 15px;
    }
</style>
</body>
</html>