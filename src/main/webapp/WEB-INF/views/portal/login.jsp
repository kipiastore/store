<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <meta charset="UTF-8"/>
    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet"/>
</head>
<body>
    <div class="login">
        <h1>Вход</h1>
        <form class="form" method="post" action="login/action">
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
            <span class="err">
                <b><%= request.getParameter("msg") == null ? "" : URLDecoder.decode(request.getParameter("msg"), "UTF-8") %></b>
            </span>
        </form>
    </div>
    <div class="copyright">
        <p><a href="home">Главная страница</a></p>
        <p>Copyright &copy; 2016. Created by <a href="">MyFrameTeam</a></p>
    </div>
</body>
</html>
