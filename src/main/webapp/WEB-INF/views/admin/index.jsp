<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
    <title>Админка</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/admin.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>
<body>

    <div class="logout">
        <c:url value="/login" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()">Выход</a>
        </c:if>
    </div>

    <div class="header">
        <div class="headerMenu">
            <div class="headerMenuContent">
                <a class="headerMenuItem" href="">Фирмы</a>
                <a class="headerMenuItem" href="">Позиции</a>
                <a class="headerMenuItem" href="">Разделы</a>
                <a class="headerMenuItem" href="">Пользователи</a>
                <a class="headerMenuItem" href="">Менеджеры</a>
                <a class="headerMenuItem" href="">Районы</a>
                <a class="headerMenuItem" href="">Пакеты</a>
                <a class="headerMenuItem" href="">Отчеты</a>
            </div>
        </div>
    </div>

    <div class="body">
        <div class="pageMenu">

        </div>

        <div class="menuBody">

            <h2>Add user</h2>
            <form:form action="${prefix}addmanager" modelAttribute="user" method="post" id="ManagerForm" >
                <table>
                    <tr>
                        <td>User name:</td>
                        <td>
                            <input title="Enter your username" type="text" required pattern="\w+"
                                   name="username" ><c:if test="${not empty error}">${error}</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>User Password:</td>
                        <td>
                            <input title="Password must contain at least 6 characters, including UPPER/lowercase and numbers"
                                   type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password"
                                   onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
                            if(this.checkValidity()) form.password2.pattern = this.value;">
                        </td>
                    </tr>
                    <tr>
                        <td>Confirm Password:</td>
                        <td>
                            <input title="Please enter the same Password as above" type="password" required
                                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password2"
                                   onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');">
                        </td>
                    </tr>
                    <tr>
                        <td> User Role:</td>
                        <td>
                            <select name="role">
                                <option selected value="SELECT"> Select Role</option>
                                <option>ROLE_MANAGER</option>
                                <option>ROLE_OPERATOR</option>
                                <option>ROLE_CLIENT</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="Create user" />
            </form:form>
            <form:form  action="${prefix}deletemanager"  method="post" id="DeleteManagerForm">
                <h2>Delete user</h2>
                <table>
                    <select name="username">
                        <option selected value="SELECT">Select</option>
                        <c:forEach var="item" items="${userlist}">
                            <option>${item}</option>
                        </c:forEach>
                    </select>
                </table>
                <input type="submit" value="Delete user" />
            </form:form>
        </div>
    </div>

    <div class="menu">
        <div class="menuTitle">
            <span class="menuTitleText">Что-то</span>
        </div>
        <div class="menuBody">

        </div>
    </div>

</body>
</html>
