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
            <form:form action="adduser" modelAttribute="user" method="post" id="createUserForm">
                <label>ФИО</label>
                <input title="Введите ФИО." type="text" name="fullName">
                <label>Логин</label><span class="error"><c:if test="${not empty error}">${error}</c:if></span>
                <input title="Имя пользователя может содержать латинские буквы, цифры, знаки дефиса, подчеркивания. От 4 до 20 символов."
                       type="text" required pattern="(^[\w+]{4,20}$)" name="username">
                <label>Пароль</label>
                <input title="Пароль должен содержать не менее 6 символов, включая верхний/нижний регистр и цифры."
                       type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password"
                       onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
                            if(this.checkValidity()) form.password2.pattern = this.value;">
                <label>Подтвердите пароль</label>
                <input title="Пожалуйста, введите тот же пароль, как указано выше." type="password" required
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password2"
                       onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');">
                <label>Роль</label>
                <select name="role" title="">
                    <option value="ROLE_MANAGER">Менеджер</option>
                    <option value="ROLE_OPERATOR">Оператор</option>
                    <option value="ROLE_CLIENT">Клиент</option>
                </select>
                <label>Доступ</label>
                <select name="status" title="">
                    <option value="opened">Открыт</option>
                    <option value="closed">Закрыт</option>
                </select>
                <input type="submit" value="Создать" />
            </form:form>
            <form:form action="updateuser" modelAttribute="user" method="post" id="updateUserForm">
                <label>ФИО</label>
                <span class="updateError"><c:if test="${not empty error}">${error}</c:if></span>
                <input id="fullName" title="Введите ФИО." type="text" name="fullName">
                <label>Логин</label>
                <input id="username" title="Имя пользователя может содержать латинские буквы, цифры, знаки дефиса, подчеркивания. От 4 до 20 символов."
                       type="text" required pattern="(^[\w+]{4,20}$)" name="username">
                <label>Новый пароль</label>
                <input id="password" title="Пароль должен содержать не менее 6 символов, включая верхний/нижний регистр и цифры."
                       type="password"  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password"
                       onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
                            if(this.checkValidity()) form.password2.pattern = this.value;">
                <label>Подтвердите новый пароль</label>
                <input id="password2" title="Пожалуйста, введите тот же пароль, как указано выше." type="password"
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password2"
                       onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');">
                <label>Роль</label>
                <select id="role" name="role" title="">
                    <option value="ROLE_MANAGER">Менеджер</option>
                    <option value="ROLE_OPERATOR">Оператор</option>
                    <option value="ROLE_CLIENT">Клиент</option>
                </select>
                <label>Доступ</label>
                <select id="accessStatus" name="status" title="">
                    <option value="opened">Открыт</option>
                    <option value="closed">Закрыт</option>
                </select>
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
        <form:form action="deleteuser" method="post" id="deleteUserForm">
            <c:forEach var="userItem" items="${model.userItems}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemInfo" id="ID-${userItem.userName}">
                        ${userItem.userName}
                    </div>
                    <div class="menuBodyItemButt">
                        <div class="menuBodyItemButtDel" id="ID-${userItem.userName}"></div>
                    </div>
                </div>
            </c:forEach>
            <input type="hidden" name="username" id="deleteUser" />
        </form:form>
    </div>
</div>
<span class="dataJson">${model.usersJson}</span>
<script type="text/javascript" src="<c:url value="/resources/js/admin.js" />"></script>
</body>
</html>
