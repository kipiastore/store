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
    <a href="${prefix}partition/${model.subPartitionItem.partitionItem.partitionId}">
        ${model.subPartitionItem.partitionItem.partitionName}
    </a>
    <span>&nbsp;&gt;&nbsp;</span>
    <a href="${prefix}subpartition/${model.subPartitionItem.subPartitionId}">
        ${model.subPartitionItem.subPartitionName}
    </a>
    <span>&nbsp;&gt;&nbsp;</span>
    <a>${model.companyName}</a>
</h3>
<div class="menu">
    <div class="content">
        <span>Название: ${model.company.name}</span>
    </div>
    <!--
    <div class="content">
        <span>Менеджер: ${model.managerName}</span>
    </div>
    <div class="content">
        <span>Пакет: ${model.packageName}</span>
    </div>
    -->
    <div class="content">
        <span>E-mail: ${model.company.email}</span>
    </div>
    <div class="content">
        <span>Телефон: ${model.company.phone}</span>
    </div>
    <div class="content">
        <span>Факс: ${model.company.fax}</span>
    </div>
    <div class="content">
        <span>Контактное лицо: ${model.company.contactPerson}</span>
    </div>
    <div class="content">
        <span>Сайт: <a href="${model.company.site}" target="_blank">${model.company.site}</a></span>
    </div>
    <div class="infoContent">
        <span>Информация: ${model.company.description}</span>
    </div>
    <div class="empDiv"></div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Район</th>
            <th>Адрес</th>
            <th>Телефоны</th>
            <th>Информация</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${model.companyAddresses}">
            <tr>
                <td>${model.idToRegionName.get(item.regionId)}</td>
                <td>${item.address}</td>
                <td>${item.phones}</td>
                <td>${item.information}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="empDiv">Позиции</div>
    <c:forEach var="item" items="${model.subPartitions}">
        <div class="content">
            <span>${item.name}</span>
        </div>
    </c:forEach>
    <div style="height: 20px; width: 100%;float: left;"></div>
</div>
<%@include file="/WEB-INF/views/operator/components/searchwindow.jspf"%>
<%@include file="/WEB-INF/views/operator/components/bottombar.jspf"%>
<script type="text/javascript" src="<c:url value="/resources/js/operator.js" />"></script>
</body>
</html>
