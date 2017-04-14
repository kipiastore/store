<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Пакеты</title>
<link rel="stylesheet" href="<c:url value="/resources/css/admin.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<body>
<%@include file="/WEB-INF/views/admin/components/logout.jspf"%>
<%@include file="/WEB-INF/views/admin/components/header.jspf"%>
<div class="body">

    <div class="pageMenu">
        <div class="pr"></div>
        <!--
        <span class="pageMenuButt">Добавить</span>
        -->
    </div>
    <div class="menuBody">
        <div class="generalContent">
            <!--
            <span class="error" id="addError">${addError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>
            <form:form action="addpackage" modelAttribute="package" method="post" id="createForm">
                <label>Название<span class="required">*</span></label>
                <input autofocus title="Введите название пакета." type="text" maxlength="120" name="name" required />
                <label>Приоритет<span class="required">*</span></label>
                <input title="Введите приоритет пакета. Не больше 9 цифр." pattern="(^[\d+]{1,9}$)" type="text" name="priority" required />
                <label>Стоимость<span class="required">*</span></label>
                <input title="Введите стоимость пакета. Не больше 9 цифр." pattern="(^[\d+]{1,9}$)" type="text" name="cost" required />
                <label>Кол. Позиций<span class="required">*</span></label>
                <input title="Введите кол. позиций пакета. Не больше 9 цифр." pattern="(^[\d+]{1,9}$)" type="text" name="numOfPositions" required />
                <input type="hidden" name="owner" value="${pageContext.request.userPrincipal.name}" />
                <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />
                <input type="submit" value="Добавить" />
            </form:form>
            <form:form action="updatepackage" modelAttribute="package" method="post" id="updateForm">
            -->
            <label>Название</label>
            <input disabled type="text" maxlength="120" name="name" id="name" required />
            <label>Приоритет</label>
            <input disabled type="text"type="text" name="priority" id="priority" required />
            <label>Стоимость</label>
            <input disabled type="text" name="cost" id="cost" required />
            <label>Кол. Позиций</label>
            <input disabled type="text"name="numOfPositions" id="numOfPositions" required />
            <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />
            <input type="hidden" name="hiddenId" id="hiddenId" />
            <!--
                <input type="submit" value="Обновить" />
            </form:form>
            -->
        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Пакеты</span>
    </div>
    <div class="menuBody">
        <form:form action="deletepackage" method="post" id="deleteForm">
            <c:forEach var="item" items="${model.packageItems}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemInfo" id="ID-${item.id}">
                        <span id="ID-${item.id}">Приоритет: ${item.priority}</span><br/>
                        <span id="ID-${item.id}">${item.name}</span>
                    </div>
                    <!--
                    <div class="menuBodyItemButt">
                        <div class="menuBodyItemButtDel" id="ID-${item.id}"></div>
                    </div>
                     -->
                </div>
            </c:forEach>
            <input type="hidden" name="packageId" id="deleteKey" />
        </form:form>

    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/admin/adminPackages.js" />"></script>
</body>
</html>
