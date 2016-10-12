<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="k" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<html>
<head>
    <title>Вход</title>
    <meta charset="UTF-8"/>
    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet"/>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
    <div class="login">
        <form class="form" method="post" action="<k:url value='/j_spring_security_check' />">
            <p class="field">
                <input type="text" name="username" placeholder="Логин" required/>
            </p>
            <p class="field">
                <input type="password" name="password" placeholder="Пароль" required/>
            </p>
            <div class="g-recaptcha" data-sitekey="6Ldf0AgUAAAAACdVi3u5AiWVjPXsRlQazQZUQss4"></div>
            <p class="submit">
                <input type="submit" name="sent" value="Войти">
            </p>
            <k:if test="${not empty error}">
                <b><span class="err">${error}</span></b>
            </k:if>
            <k:if test="${not empty msg}">
                <b><span class="err">${msg}</span></b>
            </k:if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
    <div class="copyright">
        <p><a href="index">Главная страница</a></p>
    </div>
</body>
</html>
