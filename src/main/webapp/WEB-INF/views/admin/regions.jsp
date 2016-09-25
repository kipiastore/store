<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Районы</title>
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
            <form:form action="addregion" modelAttribute="region" method="post" id="createForm">
                <label>Район</label>
                <input title="Введите название района." type="text" style="text-transform : uppercase;"
                       name="name" required />
                <input type="hidden" name="owner" value="${pageContext.request.userPrincipal.name}" />
                <input type="submit" value="Добавить" />
            </form:form>
        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Районы</span>
    </div>
    <div class="menuBody">
        <form:form action="deleteregion" method="post" id="deleteUserForm">
            <c:forEach var="item" items="${model.regionItems}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemInfo" style="cursor: auto;" id="ID-${item.id}">
                        <span class="soloTest" id="ID-${item.id}">${item.name}</span>
                    </div>
                    <div class="menuBodyItemButt">
                        <div class="menuBodyItemButtDel" id="ID-${item.id}"></div>
                    </div>
                </div>
            </c:forEach>
            <input type="hidden" name="regionId" id="deleteKey" />
        </form:form>
    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/adminUsers.js" />"></script>
</body>
</html>
