<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Позиции</title>
<link rel="stylesheet" href="<c:url value="/resources/css/director.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/multi-select.css" />" >
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.structure.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.theme.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datapicker/datepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datapicker/jquery-ui.js" />"></script>
<body>
<%@include file="/WEB-INF/views/director/components/logout.jspf"%>
<%@include file="/WEB-INF/views/director/components/header.jspf"%>

<div class="body">
    <div class="pageMenu">
        <div class="pr">
            <form:form action="positionsearchcompany" method="post" id="searchForm">
                <span class="searchButt"><img class="searchIcon" src="<c:url value="/resources/images/search.png" />"/></span>
                <input type="text" maxlength="120" name="name" placeholder="Фирма"/>
                <input type="text" maxlength="120" name="legalName" placeholder="Юр. Назв."/>
                <input type="text" maxlength="120" name="phone" placeholder="Тел."/>
                <input type="text" maxlength="120" name="contractNum" placeholder="№ Дог."/>
                <input type="text" maxlength="120" name="email" placeholder="e-mail"/>
            </form:form>
        </div>
        <span class="pageMenuButt" style="opacity: 0; cursor: default; width: 0px">С</span>
    </div>
    <div class="menuBody">
        <div class="pre-loading">
            <div class="loading">

            </div>
        </div>
        <div class="generalContent">
            <span class="error" id="addError">${addError}</span>
            <span class="error">${updateError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>
            <span class="localMessage"></span>
            <div class="container">
                <span class="message">${model.message}</span>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Название компании</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.companyList}">
                        <tr>
                            <td class="tableName" id="ID-${item.id}">${item.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="containerBySubpartition">
                <span class="messageSubpartition"></span>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Название компании</th>
                    </tr>
                    </thead>
                    <tbody class="tSubBody">

                    </tbody>
                </table>
            </div>
            <script>
                var partitionArray = [];
            </script>
            <c:forEach var="key" items="${model.subPartitionsGroupedByPartition2.keySet()}">
                <script>
                    var partitionItemKey = { name : '${key.fullName}', id : '${key.id}'};
                    partitionArray.push(partitionItemKey);
                </script>
            </c:forEach>
            <form:form action="addsubpartitions" method="post" id="updateForm">
                <select id='optgroup' name="positions" multiple='multiple'>
                    <option style="display: none" value="-1"></option>
                    <c:forEach var="key" items="${model.subPartitionsGroupedByPartition2.keySet()}">
                        <optgroup label='${key.fullName}'>
                            <c:forEach var="item" items="${model.subPartitionsGroupedByPartition2.get(key)}">
                                <option class="pickListItem ${key.id}" data="ID-${item.id}" value="${item.id}">${item.fullName}</option>
                            </c:forEach>
                        </optgroup>
                    </c:forEach>
                </select>
                <input type="hidden" name="companyId" id="hiddenId" />
                <input type="submit" value="Обновить" />
            </form:form>

        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Позиции</span>
    </div>
    <div class="menuBody">
        <!--
        <form:form action="" method="post" id="deleteForm">
            <c:forEach var="key" items="${model.subPartitionsGroupedByPartition.keySet()}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemHeadInfo" data-id="${key.id}">
                        <span class="soloTest" data-id="${key.id}">${key.name}</span>
                    </div>
                </div>
                <div style="display: none" id="itemsID-${key.id}">
                    <c:forEach var="item" items="${model.subPartitionsGroupedByPartition.get(key)}">
                        <div class="menuBodyItem" >
                            <div class="menuBodyItemInfo" id="ID-${item.id}">
                                <span class="soloTest" id="ID-${item.id}">${item.name}</span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </form:form>
        -->
    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.multi-select.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/director/directorPosition.js" />"></script>
<style>
    .pr {
        //float: left;
        //height: 40px;
    }
    .ms-container {
        background: transparent url(<c:url value="/resources/images/switch.png" />) no-repeat 50% 50%;
        width: auto;
    }
    @media screen and (max-width : 1136px) {
        .body .pageMenu { height: 76px; }
    }
    .ms-container .ms-list {
        height: 400px;
    }
</style>
</body>
</html>
