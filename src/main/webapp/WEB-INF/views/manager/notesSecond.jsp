<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<head>
    <title>Заметки</title>
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
            <form:form action="searchnotesbysecondpage" method="post" id="searchForm">
                <input type="text" maxlength="120" name="name" placeholder="Название"/>
                <input type="text" maxlength="120" name="phone" placeholder="Тел."/>
                <input type="text" maxlength="120" name="contractNum" placeholder="№ Дог.(где его взять?)"/>
                <input type="text" maxlength="120" name="email" placeholder="e-mail"/>
                <input type="text" maxlength="120" name="position" placeholder="position"/>
                <span class="searchButt"><img class="searchIcon" src="<c:url value="/resources/images/search.png" />"/></span>
            </form:form>
        </div>

    </div>
    <div class="menuBody">
        <div class="generalContent">
            <div class="container">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th id="thCompanyNameNoteBySearchMenu">Название</th>
                        <th id="thCompanyHistoryNoteBySearchMenu">История</th>
                        <th id="thCompanyPeriodNoteBySearchMenu">Срок</th>
                        <th id="thCompanyAmountNoteBySearchMenu">Сумма</th>
                        <th id="thCompanyNoteMainBySearchMenu">Примечание</th>
                        <th id="thCompanyLastNoteBySearchMenu">Последняя заметка</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.companyList}">
                        <tr>
                            <td>${item.nameForNotes}</td>
                            <td>${item.historyOfNote}</td>
                            <td>${item.periodOfContract}</td>
                            <td>${item.debt}</td>
                            <td>${item.noteMain}</td>
                            <td>${item.note}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div id="lineBetweenTables">
                </div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td id="tdCompanyDateNoteBySearchMenu">Дата</td>
                        <td id="tdCompanyHourNoteBySearchMenu">Время</td>
                        <td id="tdCompanyNameNoteBySearchMenu">Название</td>
                        <td id="tdCompanyLastNoteBySearchMenu">Тип заметки</td>
                        <td id="tdCompanyCommentNoteBySearchMenu">Комментарий</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.reminderList}">
                        <tr>
                            <td  id="ID-${item.id}">${item.dateOfNote}</td>
                            <td>${item.hourOfNote}</td>
                            <td>${item.name}</td>
                            <td>${item.typeOfNote}</td>
                            <td>${item.commentOfNote}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/manager.js" />"></script>
<%@include file="/WEB-INF/views/manager/components/menu.jspf"%>
</body>
</html>
