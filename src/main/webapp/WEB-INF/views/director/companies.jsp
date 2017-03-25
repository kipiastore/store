<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Фирмы</title>
<link rel="stylesheet" href="<c:url value="/resources/css/director.css" />"/>
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
            <form:form action="searchcompany" method="post" id="searchForm">
                <span class="searchButt"><img class="searchIcon" src="<c:url value="/resources/images/search.png" />"/></span>
                <input type="text" maxlength="120" name="name" placeholder="Фирма"/>
                <input type="text" maxlength="120" name="legalName" placeholder="Юр. Назв."/>
                <input type="text" maxlength="120" name="phone" placeholder="Тел."/>
                <input type="text" maxlength="120" name="contractNum" placeholder="№ Дог."/>
                <input type="text" maxlength="120" name="email" placeholder="e-mail"/>
            </form:form>
        </div>
        <span class="pageMenuButt">Создать</span>
    </div>
    <div class="menuBody">
        <div class="generalContent">

            <span class="error" id="addError">${addError}</span>
            <span class="error">${updateError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>

            <div class="container">
                <span class="message">${model.message}</span>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Название</th>
                            <th>Менеджер</th>
                            <th>Пакет</th>
                            <th>Истекает</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${model.companyList}">
                            <tr>
                                <td class="tableName" id="ID-${item.id}">${item.name}</td>
                                <td>${item.manager}</td>
                                <td>${item.aPackage}</td>
                                <td>${item.dateOfEndContract}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <form:form action="addcompany" modelAttribute="company" method="post" id="createForm">
                <div class="left-body">
                    <label>Название<span class="required">*</span></label>
                    <input title="Введите название фирмы." autofocus type="text" maxlength="255" name="name" id="newName" required />
                    <label>Keywords</label>
                    <input title="Введите keywords." type="text" maxlength="255" placeholder="" name="Keywords" id="newKeywords" />
                    <label>Дата заключения договора</label>
                    <input title="Введите дату." pattern="\d\d\d\d-\d\d-\d\d\s?" type="text" name="dateOfContract" id="newDateOfContract" />
                    <label>Срок договора от</label>
                    <input title="Введите дату." pattern="\d\d\d\d-\d\d-\d\d\s?" type="text" name="dateOfStartContract" id="newDateOfStartContract" />
                    <label>Срок договора до</label>
                    <input title="Введите дату." pattern="\d\d\d\d-\d\d-\d\d\s?" type="text" name="dateOfEndContract" id="newDateOfEndContract"  />
                    <label>Менеджер<span class="required">*</span></label>
                    <select name="manager" title="" id="newManager" required>
                        <c:forEach var="item" items="${model.managers}">
                            <option value="${item.username}">${item.fullName}</option>
                        </c:forEach>
                    </select>
                    <label>Пакет<span class="required">*</span></label>
                    <select name="companyPackageId" title="" id="newCompanyPackageId" required>
                        <c:forEach var="item" items="${model.packages}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                    <label>Стоимость<span class="required">*</span></label>
                    <input title="Введите стоимость. Не больше 9 цифр." pattern="(^[\d+]{1,9}$)" type="text" name="costOf" id="newCostOf" required />
                    <label>E-mail</label>
                    <input title="Введите e-mail." type="text" maxlength="120" name="email" id="newEmail" />
                    <label>Сайт</label>
                    <input title="Введите название сайта" type="text" maxlength="90" name="site" id="newSite" />
                    <label>Информация</label>
                    <textarea rows="4" name="description" maxlength="1024" id="newDescription"></textarea>
                </div>
                <div class="right-body">

                    <div class="check-box-block">
                        <input type="checkbox" name="isShowForOperator" />
                        <label>Вкл. в справ.</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isShowForSite" />
                        <label>Вкл. на сайте</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isPaid" />
                        <label>Оплачен</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isRedirect" />
                        <label>Переадресация</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isOffPosition" />
                        <label>Откл. позиции</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isClosed" />
                        <label>Закрыты</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isPriority" />
                        <label>Приоритет</label>
                    </div>

                    <div class="address-list"></div>
                    <div class="openRequisites">Реквизиты</div>
                    <div class="requisites">
                        <label>Юридическое название</label>
                        <input title="Введите юридическое название фирмы." maxlength="255" type="text" name="legalName" id="newLegalName" />
                        <label>ИНН</label>
                        <input title="Введите идентификационный код. Не больше 9 цифр." pattern="(^[\d+]{1,9}$)" type="text" name="inn" id="newInn" />
                        <label>Юридический адрес</label>
                        <input title="Введите юридический адрес фирмы." maxlength="120" type="text" name="legalAddress" id="newLegalAddress" />
                        <label>Телефон</label>
                        <input title="Введите номер телефона." maxlength="15" type="text" name="phone" id="newPhone" />
                        <label>Факс</label>
                        <input title="Введите номер факса." maxlength="15" type="text" name="fax" id="newFax" />
                        <label>ФИО Директора</label>
                        <input title="Введите ФИО директора." maxlength="120" type="text" name="directorFullName" id="newDirectorFullName" />
                        <label>Контактное лицо</label>
                        <input title="Введите контактное лицо." maxlength="120" type="text" name="contactPerson" id="newContactPerson"/>
                    </div>
                </div>
                <input type="hidden" name="owner" value="${pageContext.request.userPrincipal.name}" />
                <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />

                <%@include file="/WEB-INF/views/director/components/address.jspf"%>

                <input type="submit" value="Создать" />
            </form:form>

            <form:form action="updatecompany" modelAttribute="company" method="post" id="updateForm">
                <div class="left-body">
                    <label>Название<span class="required">*</span></label>
                    <input title="Введите название фирмы." maxlength="120" type="text" name="name" id="name"required  />
                    <label>Keywords</label>
                    <input title="Введите keywords." maxlength="255" placeholder="Keywords" type="text" name="keywords" id="keywords" />
                    <label>Дата заключения договора<span class="required"></span></label>
                    <input title="Введите дату." pattern="\d\d\d\d-\d\d-\d\d\s?" type="text" name="dateOfContract" id="dateOfContract"  />
                    <label>Срок договора от<span class="required"></span></label>
                    <input title="Введите дату." pattern="\d\d\d\d-\d\d-\d\d\s?" type="text" name="dateOfStartContract" id="dateOfStartContract"  />
                    <label>Срок договора до<span class="required"></span></label>
                    <input title="Введите дату." pattern="\d\d\d\d-\d\d-\d\d\s?" type="text" name="dateOfEndContract" id="dateOfEndContract"  />
                    <label>Менеджер<span class="required">*</span></label>
                    <select name="manager" title="" id="manager" required>
                        <c:forEach var="item" items="${model.managers}">
                            <option value="${item.username}">${item.fullName}</option>
                        </c:forEach>
                    </select>
                    <label>Пакет<span class="required">*</span></label>
                    <select name="companyPackageId" title="" id="companyPackageId" required>
                        <c:forEach var="item" items="${model.packages}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                    <label>Стоимость<span class="required">*</span></label>
                    <input title="Введите стоимость. Не больше 9 цифр." pattern="(^[\d+]{1,9}$)" type="text" name="costOf" id="costOf" required />
                    <label>E-mail</label>
                    <input title="Введите e-mail." type="text" maxlength="120" name="email" id="email" />
                    <label>Сайт</label>
                    <input title="Введите название сайта" type="text" maxlength="90" name="site" id="site" />
                    <label>Информация</label>
                    <textarea rows="4" name="description" maxlength="1024" id="description"></textarea>
                </div>
                <div class="right-body">

                    <div class="check-box-block">
                        <input type="checkbox" name="isShowForOperator" id="isShowForOperator"/>
                        <label>Вкл. в справ.</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isShowForSite" id="isShowForSite"/>
                        <label>Вкл. на сайте</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isPaid" id="isPaid"/>
                        <label>Оплачен</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isRedirect" id="isRedirect"/>
                        <label>Переадресация</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isOffPosition" id="isOffPosition"/>
                        <label>Откл. позиции</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isClosed" id="isClosed"/>
                        <label>Закрыты</label>
                    </div>
                    <div class="check-box-block">
                        <input type="checkbox" name="isPriority" />
                        <label>Приоритет</label>
                    </div>

                    <div class="address-list"></div>
                    <div class="openRequisites">Реквизиты</div>
                    <div class="requisites">
                        <label>Юридическое название</label>
                        <input title="Введите юридическое название фирмы." maxlength="120" type="text" name="legalName" id="legalName" />
                        <label>ИНН</label>
                        <input title="Введите идентификационный код. Не больше 9 цифр." pattern="(^[\d+]{1,9}$)" type="text" name="inn" id="inn"/>
                        <label>Юридический адрес</label>
                        <input title="Введите юридический адрес фирмы." maxlength="120" type="text" name="legalAddress" id="legalAddress"/>
                        <label>Телефон</label>
                        <input title="Введите номер телефона." maxlength="15" type="text" name="phone" id="phone" />
                        <label>Факс</label>
                        <input title="Введите номер факса." maxlength="15" type="text" name="fax" id="fax"/>
                        <label>ФИО Директора</label>
                        <input title="Введите ФИО директора." maxlength="120" type="text" name="directorFullName" id="directorFullName" />
                        <label>Контактное лицо</label>
                        <input title="Введите контактное лицо." maxlength="120" type="text" name="contactPerson" id="contactPerson"/>
                    </div>
                </div>
                <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />
                <input type="hidden" name="hiddenId" id="hiddenId" />
                <%@include file="/WEB-INF/views/director/components/updateAddress.jspf"%>
                <input type="submit" value="Обновить" />
            </form:form>
        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Фирмы</span>
    </div>
    <div class="menuBody">
        <form:form action="deletecompany" method="post" id="deleteForm">
            <c:forEach var="key" items="${model.filterListMap.keySet()}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemHeadInfo" data-id="${key.id}">
                        <span class="soloTest" data-id="${key.id}">${key.name}</span>
                    </div>
                </div>
                <div style="display: none" id="itemsID-${key.id}">
                    <c:forEach var="item" items="${model.filterListMap.get(key)}">
                        <div class="menuBodyItem" >
                            <div class="menuBodyItemInfo" id="ID-${item.id}">
                                <span id="ID-${item.id}">Договор до: ${item.dateOfEndContract}</span><br/>
                                <span id="ID-${item.id}">${item.name}</span>
                            </div>
                            <div class="menuBodyItemButt">
                                <div class="menuBodyItemButtDel" id="ID-${item.id}"></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
            <input type="hidden" name="companyId" id="deleteKey" />
        </form:form>
    </div>
</div>

<span class="addingUserJson">${model.addingCompanyJson}</span>
<span class="companyAddressJson">${model.companyAddressJson}</span>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>

<script type="text/javascript" src="<c:url value="/resources/js/directorUsers.js" />"></script>
<style>
    @media screen and (max-width : 1215px) {
        .body .pageMenu { height: 76px; }
    }
</style>
</body>
</html>
