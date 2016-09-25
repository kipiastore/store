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
        <span class="pageMenuButt">Добавить</span>
    </div>
    <div class="menuBody">
        <div class="generalContent">
            <span class="error" id="addError">${addError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>
            <form:form action="addpackage" modelAttribute="package" method="post" id="createForm">
                <label>Название</label>
                <input title="Введите название пакета." type="text" name="name" required />
                <label>Приоритет</label>
                <input title="Введите приоритет пакета." type="number" name="priority" required />
                <input type="hidden" name="owner" value="${pageContext.request.userPrincipal.name}" />
                <input type="submit" value="Добавить" />
            </form:form>
        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Пакеты</span>
    </div>
    <div class="menuBody">
        <form:form action="deletepackage" method="post" id="deleteUserForm">
            <c:forEach var="item" items="${model.packageItems}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemInfo" style="cursor: auto;" id="ID-${item.id}">
                        <span id="ID-${item.id}">Приоритет: ${item.priority}</span><br/>
                        <span id="ID-${item.id}">${item.name}</span>
                    </div>
                    <div class="menuBodyItemButt">
                        <div class="menuBodyItemButtDel" id="ID-${item.id}"></div>
                    </div>
                </div>
            </c:forEach>
            <input type="hidden" name="packageId" id="deleteKey" />
        </form:form>
    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/adminUsers.js" />"></script>
</body>
</html>
