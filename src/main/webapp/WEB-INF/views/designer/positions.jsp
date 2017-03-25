<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Позиции</title>
<link rel="stylesheet" href="<c:url value="/resources/css/designer.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/multi-select.css" />" >
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.structure.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.theme.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datapicker/datepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datapicker/jquery-ui.js" />"></script>
<body>
<%@include file="/WEB-INF/views/designer/components/logout.jspf"%>
<%@include file="/WEB-INF/views/designer/components/header.jspf"%>

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
                            <td class="tableName" id="ID-${item.id}"><a href="positions/company/${item.id}">${item.name}</a></td>
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

            <form:form action="addsubpartitioninfo" method="post" id="updateForm">

            </form:form>
        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Позиции</span>
    </div>
    <div class="menuBody">

    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.multi-select.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/designerPosition.js" />"></script>
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
</style>
</body>
</html>
