<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<head>
    <title>Должники</title>
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
            <form:form action="searchcompanybydebtors" method="post" id="searchForm">
                <input type="text" maxlength="120" name="name" placeholder="Название"/>
                <input type="text" maxlength="120" name="phone" placeholder="Тел."/>
                <input type="text" maxlength="120" name="contractNum" placeholder="№ Дог.(где его взять?)"/>
                <input type="text" maxlength="120" name="email" placeholder="e-mail"/>
                <input type="text" maxlength="120" name="position" placeholder="position"/>
                <select name="selectSearchCompany" id="allComment">
                    <option selected value="searchAllCompany" >Все</option>
                    <option value="withCommentCompany">С замечаниями</option>
                    <option value="noCommentCompany">Без замечаний</option>
                </select>
                <select name="selectSearchCompanyByPaymentStatus" id="selectPaymentStatus">
                    <option selected value="selectSearchCompanyByPaymentStatusAll" >Все</option>
                    <option value="withCommentCompany">Без договора</option>
                    <option value="noCommentCompany">Оплаченные</option>
                    <option value="noCommentCompany">Не оплаченные</option>
                </select>
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
                        <th id="thCompanyDebtor">Фирма</th>
                        <th id="thPackageDebtor">Пакет</th>
                        <th id="thDebtor">Долг</th>
                        <th id="thDirectorNameDebtor">Директор</th>
                        <th id="thAdressDebtor">Адрес</th>
                        <th id="thNoteDebtor">Последняя заметка</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.companyList}">
                        <tr>
                            <td  id="ID-${item.id}">${item.name}</td>
                            <td>${item.companyPackage}</td>
                            <td>${item.debt}</td>
                            <td>${item.directorFullName}</td>
                            <td>
                        <c:forEach var="itemAdress" items="${item.companyAddresses}">
                          ${itemAdress} <br>
                        </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="itemNote" items="${item.note}">
                                    ${itemNote} <br>
                                </c:forEach>
                            </td>
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
