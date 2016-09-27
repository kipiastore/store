<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Фирмы</title>
<link rel="stylesheet" href="<c:url value="/resources/css/admin.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<body>
<%@include file="/WEB-INF/views/admin/components/logout.jspf"%>
<%@include file="/WEB-INF/views/admin/components/header.jspf"%>
<div class="body">
    <div class="pageMenu">
        <div class="pr"></div>
        <span class="pageMenuButt">Создать</span>
    </div>
    <div class="menuBody">
        <div class="generalContent">
            <span class="error" id="addError">${addError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>
            <form:form action="addcompany" modelAttribute="company" method="post" id="createForm">
                <label>Название</label>
                <input title="Введите название фирмы." type="text" name="name" required />
                <label>Ключевые слова</label>
                <input title="Введите ключевые слова." type="text" name="keywords" />
                <label>Дата заключения договора MM-dd-yyyy</label>
                <input title="Введите дату." type="text" name="dateOfContract" required />
                <label>Срок договора от MM-dd-yyyy</label>
                <input title="Введите дату." type="text" name="dateOfStartContract" required />
                <label>Срок договора до MM-dd-yyyy</label>
                <input title="Введите дату." type="text" name="dateOfEndContract" required />
                <label>Менеджер</label>
                <select name="manager" title="" >
                    <option value="opened">Sasha</option>
                    <option value="closed">Vova</option>
                </select>
                <label>Пакет</label>
                <select name="companyPackage" title="" >
                    <option value="opened">bestpack</option>
                    <option value="closed">dnopack</option>
                </select>
                <label>Стоимость</label>
                <input title="Введите стоимость." type="number" name="costOf" required />
                <label>Юридическое название</label>
                <input title="Введите юридическое название фирмы." type="text" name="legalName" />
                <label>ИНН</label>
                <input title="Введите идентификационный код." type="number" name="inn" />
                <label>Юридический адрес</label>
                <input title="Введите юридический адрес фирмы." type="text" name="legalAddress" />
                <label>Телефон</label>
                <input title="Введите номер телефона." type="text" name="phone" />
                <label>Факс</label>
                <input title="Введите номер факса." type="text" name="fax" />
                <label>ФИО Директора</label>
                <input title="Введите ФИО директора." type="text" name="directorFullName"  />
                <label>Контактное лицо</label>
                <input title="Введите номер факса." type="text" name="contactPerson" />
                <input type="hidden" name="owner" value="${pageContext.request.userPrincipal.name}" />
                <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />
                <input type="submit" value="Добавить" />
            </form:form>
        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Фирмы</span>
    </div>
    <div class="menuBody">
        <form:form action="deletecompany" method="post" id="deleteUserForm">
            <c:forEach var="item" items="${model.companiesItems}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemInfo" id="ID-${item.id}">
                        <span id="ID-${item.id}">${item.name}</span><br/>
                        <span id="ID-${item.id}">${item.name}</span>
                    </div>
                    <div class="menuBodyItemButt">
                        <div class="menuBodyItemButtDel" id="ID-${item.id}"></div>
                    </div>
                </div>
            </c:forEach>
            <input type="hidden" name="companyId" id="deleteKey" />
        </form:form>
    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/adminUsers.js" />"></script>
</body>
</html>
