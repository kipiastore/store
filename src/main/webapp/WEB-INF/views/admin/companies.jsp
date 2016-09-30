<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Фирмы</title>
<link rel="stylesheet" href="<c:url value="/resources/css/admin.css" />"/>
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
        <div class="pr"></div>
        <span class="pageMenuButt">Создать</span>
    </div>
    <div class="menuBody">
        <div class="generalContent">

            <span class="error" id="addError">${addError}</span>
            <span class="error">${updateError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>

            <form:form action="addcompany" modelAttribute="company" method="post" id="createForm">
                <div class="left-body">
                    <label>Название<span class="required">*</span></label>
                    <input title="Введите название фирмы." type="text" maxlength="120" name="name" id="newName" required />
                    <label>Ключевые слова</label>
                    <input title="Введите ключевые слова." type="text" maxlength="255" placeholder="Через точку с запятой: авто; ремонт;" type="text" name="keywords" id="newKeywords" />
                    <label>Дата заключения договора<span class="required">*</span></label>
                    <input title="Введите дату." type="text" name="dateOfContract" id="newDateOfContract" required />
                    <label>Срок договора от<span class="required">*</span></label>
                    <input title="Введите дату." type="text" name="dateOfStartContract" id="newDateOfStartContract" required />
                    <label>Срок договора до<span class="required">*</span></label>
                    <input title="Введите дату." type="text" name="dateOfEndContract" id="newDateOfEndContract" required />
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
                    <input title="Введите стоимость. Не больше 10 цифр." pattern="(^[\d+]{1,10}$)" type="text" name="costOf" id="newCostOf" required />
                    <label>Информация</label>
                    <textarea rows="3" name="description" maxlength="255" id="newDescription"></textarea>
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

                    <div class="address-list"></div>
                    <div class="openRequisites">Реквизиты</div>
                    <div class="requisites">
                        <label>Юридическое название</label>
                        <input title="Введите юридическое название фирмы." maxlength="120" type="text" name="legalName" id="newLegalName" />
                        <label>ИНН</label>
                        <input title="Введите идентификационный код. Не больше 10 цифр." pattern="(^[\d+]{1,10}$)" type="text" name="inn" id="newInn" />
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

                <%@include file="/WEB-INF/views/admin/components/address.jspf"%>

                <input type="submit" value="Создать" />
            </form:form>

            <form:form action="updatecompany" modelAttribute="company" method="post" id="updateForm">
                <div class="left-body">
                    <label>Название<span class="required">*</span></label>
                    <input title="Введите название фирмы." maxlength="120" type="text" name="name" id="name" required />
                    <label>Ключевые слова</label>
                    <input title="Введите ключевые слова." maxlength="255" placeholder="Через точку с запятой: авто; ремонт;" type="text" name="keywords" id="keywords" />
                    <label>Дата заключения договора<span class="required">*</span></label>
                    <input title="Введите дату." type="text" name="dateOfContract" id="dateOfContract" required />
                    <label>Срок договора от<span class="required">*</span></label>
                    <input title="Введите дату." type="text" name="dateOfStartContract" id="dateOfStartContract" required />
                    <label>Срок договора до<span class="required">*</span></label>
                    <input title="Введите дату." type="text" name="dateOfEndContract" id="dateOfEndContract" required />
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
                    <input title="Введите стоимость. Не больше 10 цифр." pattern="(^[\d+]{1,10}$)" type="text" name="costOf" id="costOf" required />
                    <label>Информация</label>
                    <textarea rows="3" name="description" maxlength="255" id="description"></textarea>
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

                    <div class="address-list"></div>
                    <div class="openRequisites">Реквизиты</div>
                    <div class="requisites">
                        <label>Юридическое название</label>
                        <input title="Введите юридическое название фирмы." maxlength="120" type="text" name="legalName" id="legalName" />
                        <label>ИНН</label>
                        <input title="Введите идентификационный код." pattern="(^[\d+]{1,10}$)" type="text" name="inn" id="inn"/>
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

                <%@include file="/WEB-INF/views/admin/components/updateAddress.jspf"%>

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
                        <div class="menuBodyItem"  >
                            <div class="menuBodyItemInfo" id="ID-${item.id}">
                                <span id="ID-${item.id}">${item.dateOfEndContract}</span><br/>
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

<span class="dataJson">${model.companiesJson}</span>
<span class="addingUserJson">${model.addingCompanyJson}</span>
<span class="companyAddressJson">${model.companyAddressJson}</span>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>

<script type="text/javascript" src="<c:url value="/resources/js/adminUsers.js" />"></script>
</body>
</html>
