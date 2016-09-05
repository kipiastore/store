<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="k" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<html>
<head>
    <title>Вход</title>
    <meta charset="UTF-8"/>
    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet"/>
</head>
<body>
    <div class="login">
        <h1>Вход</h1>
        <form class="form" method="post" action="<k:url value='/j_spring_security_check' />">
            <p class="field">
                <input type="text" name="login" placeholder="Логин" required/>
                <i class="fa fa-user"></i>
            </p>
            <p class="field">
                <input type="password" name="password" placeholder="Пароль" required/>
                <i class="fa fa-lock"></i>
            </p>
            <p class="submit">
                <input type="submit" name="sent" value="Войти">
            </p>
            <k:if test="${not empty error}">
                <span class="err">${error}</span>
            </k:if>
            <k:if test="${not empty msg}">
                <span class="err">${msg}</span>
            </k:if>
            <!--
            <span class="err">
                <b><%= ""/*request.getParameter("msg") == null ? "" : URLDecoder.decode(request.getParameter("msg"), "UTF-8")*/ %></b>
            </span>
            -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
    <div class="copyright">
        <p><a href="home">Главная страница</a></p>
        <p>Copyright &copy; 2016. Created by <a href="">MyFrameTeam</a></p>
    </div>
</body>
</html>
