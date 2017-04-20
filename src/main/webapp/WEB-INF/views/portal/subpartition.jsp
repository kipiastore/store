<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="k" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Справочная Одессы | ${subPartitionName}</title>
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
        <div style="padding: 1px 0 0px 0;">
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb">${subPartitionName}</h2>
                <div class="rptShort">
                </div>
            </div>
        </div>
    </div>
    <div class="container">


        <!--
        <div class="rua-l-wrapper2" style="border-color: hsla(0,26%,66%,1)">
            <div class="companyMainInfo">
                <a data-id="1" href="../company/1">
                    <h3>Company</h3>
                </a>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;В книгео рнно тестиротирменеджеров.</span>
            </div>
            <div class="AddressList">
                <div class="address">
                    <span class="addressInfo">Приморская 33</span>&nbsp;
                    <span>063 534 34 55, 078 455 32 45</span>&nbsp;
                    <span>с пн по пт с 9 до 18</span>
                </div>
                <div class="hiddenAdr-btn">
                    <p>
                        <a class="btn btn-primary" data-id="1">Филиалы</a>
                    </p>
                </div>
                <div class="address hiddenAdr address-1">
                    <span class="addressInfo">Бунина 33</span>&nbsp;
                    <span>063 534 34 55, 078 455 32 45</span>&nbsp;
                    <span>с пн по пт с 9 до 18</span>
                </div>
            </div>
        </div>
        -->


    </div>
    <div class="container2">
        <div class="pre-loading">
            <div class="loading">

            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/views/portal/components/brand.jspf"%>
    <%@include file="/WEB-INF/views/portal/components/footer.jspf"%>
</form>
<span class="subPartitionId">${subPartitionId}</span>
<%@include file="/WEB-INF/views/portal/components/emailWindow.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/portal/portalSubPartition.js" />"></script>

</body>
</html>