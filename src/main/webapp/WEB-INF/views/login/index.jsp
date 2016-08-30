<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet" />
</head>
<body>
    <div class="login">
        <h1>Login</h1>
        <form class="form" method="post" action="#">
            <p class="field">
                <input type="text" name="login" placeholder="Username" required/>
                <i class="fa fa-user"></i>
            </p>
            <p class="field">
                <input type="password" name="password" placeholder="Password" required/>
                <i class="fa fa-lock"></i>
            </p>
            <p class="submit"><input type="submit" name="sent" value="Login"></p>
        </form>
    </div><!--/ Login-->
    <div class="copyright">
        <p>Copyright &copy; 2016. Created by <a href="http://google.com" target="_blank">Google</a></p>
    </div>
</body>
</html>
