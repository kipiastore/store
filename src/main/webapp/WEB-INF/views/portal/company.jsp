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
    <link rel="stylesheet" href="<c:url value="/resources/css/home-min2.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <style>
        .def-block {
            min-height: 320px;
        }
        .position-block {

        }
        .position-image-block {
            float: left;
        }
        .position-image {
            height: 250px;
            padding: 5px 10px 0px 10px;
            max-width: 936px;
        }
        .position-text-block {
            padding: 10px;
            text-align: justify;
            min-height: 290px;
        }
        .position-addintional-block {
            float: left;
            width: 100%;
            text-align: center;
            padding-bottom: 10px;
            padding-top: 10px;
        }
        hr {
            margin: 0px 0;
            border: 0;
            border-top: 1px solid #eee;
            border-bottom: 1px solid #fff;
        }
        .rua-l-wrapper2 {
            max-width: 960px;
            margin: 0 auto;
            border: none;
            margin-bottom: 15px;
        }
        .companyAmount {
            float: right;
            padding: 30px 0px 10px 10px;
            color: #d67b7b;
        }
    </style>
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
            <span>&nbsp;&nbsp;&nbsp;&nbsp;${company.description}</span>
            <span class="companyAmount">
                <k:if test="${not empty company.costOf}">
                    Стоимость: <b>${company.costOf}</b>
                </k:if>
            </span>
        </div>
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
        <k:forEach var="item" items="${model.companySubpartitionContentList}">
            <div class="position-main-container def-block">
                <div class="position-block">
                    <div class="position-addintional-block">
                        <span data-id="${item.companySubpartitionId}" id="subNameId-${item.id}">${item.subPartitionName}</span>
                    </div>
                    <div class="position-image-block">
                        <img class="position-image" id="imageId-${item.id}" data-id="${item.imageId}" src="../download?id=${item.imageId}" title="">
                    </div>
                    <div class="position-text-block">
                        <span id="infoId-${item.id}">${item.info}</span>
                    </div>
                    <hr/>
                </div>
            </div>
        </k:forEach>
    </div>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>