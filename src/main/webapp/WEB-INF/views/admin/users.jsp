<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Пользователи</title>
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
            <span class="error">${updateError}</span>
            <span class="error">${deleteError}</span>
            <span class="success">${successMessage}</span>

            <form:form action="adduser" modelAttribute="user" method="post" id="createForm">
                <label>ФИО</label>
                <input title="Введите ФИО." autofocus type="text" maxlength="255" name="fullName" id="newFullName" />
                <label>Логин<span class="required">*</span></label>
                <input title="Имя пользователя может содержать латинские буквы, цифры, знаки дефиса, подчеркивания. От 4 до 20 символов."
                       type="text" required pattern="(^[\w+]{4,20}$)" name="username" id="newUsername" />
                <label>Пароль<span class="required">*</span></label> <input type="checkbox"  name="showPassword" id="showPassword" /> <label>Показать пароль</label>
                <input title="Пароль должен содержать не менее 6 символов, включая верхний/нижний регистр и цифры."
                       type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password"id="password"
                       onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
                            if(this.checkValidity()) form.password2.pattern = this.value;" >
                <label>Подтвердите пароль<span class="required">*</span></label>
                <input title="Пожалуйста, введите тот же пароль, как указано выше." type="password" required
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password2" id="password2"
                       onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');" />
                <label>Доступ</label>
                <select name="status" title="" id="newStatus">
                    <option value="opened">Открыт</option>
                    <option value="closed">Закрыт</option>
                </select>

                <input type="hidden" name="owner" value="${pageContext.request.userPrincipal.name}" />
                <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />

                <input type="submit" value="Создать" />
            </form:form>

            <form:form action="updateuser" modelAttribute="user" method="post" id="updateForm">
                <label>ФИО</label>
                <input id="fullName" title="Введите ФИО." maxlength="255" type="text" name="fullName">
                <label>Логин</label>
                <input id="username" type="text" disabled pattern="(^[\w+]{4,20}$)" name="username"
                       title="Имя пользователя может содержать латинские буквы, цифры, знаки дефиса, подчеркивания. От 4 до 20 символов.">
                <label>Новый пароль</label> <input type="checkbox"  name="showPassword" id="showPasswordUpdate" /> <label>Показать пароль</label>
                <input title="Пароль должен содержать не менее 6 символов, включая верхний/нижний регистр и цифры."
                       id="passwordUpdate" type="password"  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password"
                       onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
                            if(this.checkValidity()) form.password2.pattern = this.value;">
                <label>Подтвердите новый пароль</label>
                <input id="passwordUpdate2" title="Пожалуйста, введите тот же пароль, как указано выше." type="password"
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password2"
                       onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');">
                <label>Доступ</label>
                <select id="accessStatus" name="status" title="">
                    <option value="opened">Открыт</option>
                    <option value="closed">Закрыт</option>
                </select>

                <input type="hidden" name="usernameHidden" id="usernameHidden" />
                <input type="hidden" name="lastModifiedBy" value="${pageContext.request.userPrincipal.name}" />

                <input type="submit" value="Обновить" />
            </form:form>

        </div>
    </div>
</div>
<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Пользователи</span>
    </div>
    <div class="menuBody">
        <form:form action="deleteuser" method="post" id="deleteForm">
            <c:forEach var="item" items="${model.userItems}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemInfo" id="ID-${item.userName}">
                        <span id="ID-${item.userName}">${item.userName}</span><br/>
                        <span id="ID-${item.userName}">${item.fullName}</span>
                    </div>
                    <div class="menuBodyItemButt">
                        <div class="menuBodyItemButtDel" id="ID-${item.userName}"></div>
                    </div>
                </div>
            </c:forEach>
            <input type="hidden" name="username" id="deleteKey" />
        </form:form>
    </div>
</div>
<span class="dataJson">${model.usersJson}</span>
<span class="addingUserJson">${model.addingUserJson}</span>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/admin/adminUsers.js" />"></script>
</body>
</html>
