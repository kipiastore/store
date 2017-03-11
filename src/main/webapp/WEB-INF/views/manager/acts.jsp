<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<head>
    <title>Акты</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/manager.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/managerDatepicker/jquery-ui.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/managerDatepicker/jquery-ui.structure.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/managerDatepicker/jquery-ui.theme.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/managerDatepicker/datepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/managerDatepicker/jquery-ui.js" />"></script>
</head>
<body>
<%@include file="/WEB-INF/views/manager/components/logout.jspf"%>
<%@include file="/WEB-INF/views/manager/components/header.jspf"%>
<div class="body">
    <div class="pageMenu">
        <div class="pr">
            <form:form action="searchcompanybyacts" method="post" id="searchForm">
                <input type="text" maxlength="120" name="name" placeholder="Название"/>
                <input type="text" maxlength="120" name="phone" placeholder="Тел."/>
                <input type="text" maxlength="120" name="contractNum" placeholder="№ Дог.(где его взять?)"/>
                <input type="text" maxlength="120" name="email" placeholder="e-mail"/>
                <input type="text" maxlength="120" name="position" placeholder="position"/>
                <span class="searchButt"><img class="searchIcon" src="<c:url value="/resources/images/search.png" />"/></span>
            </form:form>
            <!--
            <div class="prDatepicker">
                <form:form action="filtercompanyacts" method="post" id="filterActsForm">
                    <input type="text" maxlength="120" placeholder="Дата" id="actsDate"/>
                    <input type="submit" name="Для печати" value="Для печати">
                </form:form>
            </div>
            -->
        </div>
        <span class="pageMenuButt" style="opacity: 0; cursor: default;">Добавить</span>
    </div>
    <div class="menuBody">
        <!--
        <div class="generalContentActs">
            <div class="container">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th id="thCompanyActs">Фирма</th>
                        <th id="thManagerActs">Менеджер</th>
                        <th id="thTermOfContractActs">Срок договора</th>
                        <th id="thsumActs">Сумма</th>
                        <th id="thNotesActs">Примечание</th>
                        <th id="thDateOgPaymentActs">Оплата</th>
                        <th id="thActs">Акт</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.companyList}">
                        <tr>
                            <td  id="ID-${item.id}">${item.name}</td>
                            <td>${item.manager}</td>
                            <td>${item.timeOfContract}</td>
                            <td>${item.costOf}</td>
                            <td>${item.noteOfActs}</td>
                            <td>${item.dateOfPaid}</td>
                            <td>${item.act}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        -->
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
            <form:form action="addact" enctype="multipart/form-data" modelAttribute="report" method="post" id="hiddenCreateForm">
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
<!--<script type="text/javascript" src="<c:url value="/resources/js/manager.js" />"></script>-->
<script type="text/javascript" src="<c:url value="/resources/js/manager/managerActs.js" />"></script>
<%@include file="/WEB-INF/views/manager/components/menu.jspf"%>
</body>
</html>
