<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Отчет</title>
<link rel="stylesheet" href="<c:url value="/resources/css/admin.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/multi-select.css" />" >
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.structure.css" />"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker/jquery-ui.theme.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datapicker/datepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datapicker/jquery-ui.js" />"></script>
<body>
<%@include file="/WEB-INF/views/admin/components/logout.jspf"%>
<%@include file="/WEB-INF/views/admin/components/header.jspf"%>

<div class="body">
    <div class="pageMenu">
        <div class="pr">
            <form:form action="reportssearchcompany" method="post" id="searchForm">
                <span class="searchButt"><img class="searchIcon" src="<c:url value="/resources/images/search.png" />"/></span>
                <input type="text" maxlength="120" name="name" placeholder="Фирма"/>
                <input type="text" maxlength="120" name="legalName" placeholder="Юр. Назв."/>
                <input type="text" maxlength="120" name="phone" placeholder="Тел."/>
                <input type="text" maxlength="120" name="contractNum" placeholder="№ Дог."/>
                <input type="text" maxlength="120" name="email" placeholder="e-mail"/>
            </form:form>
        </div>
        <span class="pageMenuButt" style="opacity: 0; cursor: default;">Добавить</span>
    </div>
    <div class="menuBody">
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

            <div id="updateForm">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Название</th>
                        <th>Создан</th>
                        <th>Владелец</th>
                        <th>Описание</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="bodyReports">

                    </tbody>
                </table>
            </div>
            <form:form action="addreport" enctype="multipart/form-data" modelAttribute="report" method="post" id="hiddenCreateForm">
                <label>Название</label>
                <input autofocus title="Введите название отчета." type="text" maxlength="120" name="name" placeholder="Оставьте поле пустым если хотите чтобы использовать имя файла." />
                <label>Файл<span class="required">*</span></label>
                <input type="file" name="file" required title="txt pdf doc docx xls xlsx rar 7z gif jpeg jpg png bmp"/>
                <textarea rows="4" name="description" maxlength="255" ></textarea>
                <input type="hidden" id="companyIdAdd" name="companyId" value="" />
                <input type="hidden" name="owner" value="${pageContext.request.userPrincipal.name}" />
                <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />
                <input type="submit" value="Добавить" />
            </form:form>
        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Отчеты</span>
    </div>
    <div class="menuBody">

    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.multi-select.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/admin/adminReports.js" />"></script>
<style>
    .pr {
    //float: left;
    //height: 40px;
    }

    @media screen and (max-width : 1215px) {
        .body .pageMenu { height: 76px; }
    }
</style>
</body>
</html>
