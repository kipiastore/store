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
<%@include file="/WEB-INF/views/admin/components/logout.jspf"%>
<%@include file="/WEB-INF/views/admin/components/header.jspf"%>

    <div class="body">
        <div class="pageMenu">
            <div class="pr"></div>
            <span class="pageMenuButt">Создать</span>
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
            <span class="menuTitleText">Пользователи</span>
        </div>
        <div class="menuBody">
            <form:form  action="${prefix}deletemanager"  method="post" id="DeleteManagerForm">
                <c:forEach var="item" items="${userlist}">
                    <div class="menuBodyItem">
                        <div class="menuBodyItemInfo">
                            ${item}
                        </div>
                        <div class="menuBodyItemButt">
                            <div class="menuBodyItemButtDel"></div>
                        </div>
                    </div>
                </c:forEach>
            </form:form>
        </div>
    </div>

</body>
</html>
