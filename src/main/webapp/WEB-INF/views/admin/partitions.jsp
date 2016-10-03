<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<title>Разделы</title>
<link rel="stylesheet" href="<c:url value="/resources/css/admin.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/showPartition.js" />"></script>
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
            <form:form action="addpartition" modelAttribute="partition"  method="post" id="createForm">
            <label>Название раздела/подраздела</label>
            <input title="Введите название раздела." type="text" name="name" required />
            <label>Тип раздел/подраздел</label>
            <select name="partitionLevel">
                <option selected >Выбирите уровень раздела</option>
                <option value="1" id="partition">Раздел</option>
                <option value="2" id="subPartition">Подраздел</option>
            </select>
                <div id="show"style="display:none">
                    <label>Выбирите название раздела к которому будет пренадлижать создаваемый подрездел</label>
                    <select name="namePartition" >
                        <option selected>Раздел</option>
                        <c:forEach var="item" items="${partitions}">
                            <option>${item}</option>
                        </c:forEach>
                    </select>
                </div>
            <input type="submit" value="Добавить" />
        </form:form>
            <form:form action="updatepartition" modelAttribute="partition" method="post" id="updateForm">
                <label>Название</label>
                <input title="Введите название пакета." type="text" name="name" required />
                <label>Пренадлежит разделу</label>
                <select name="partitionLevel">
                    <option selected >Выбирите уровень раздела</option>
                    <option value="1">Верхний уровень</option>
                    <option value="2">Средний уровень</option>
                    <option value="3">Нижний уровень</option>
                </select>
                <input type="submit" value="Добавить" />
            </form:form>
        </div>
    </div>
</div>

<div class="menu">
    <div class="menuTitle">
        <span class="menuTitleText">Разделы</span>
    </div>
    <div class="menuBody">
        <form:form action="deletepartition" method="post" id="deleteUserForm">
            <c:forEach var="item" items="${model.partitionItems}">
                <div class="menuBodyItem">
                    <div class="menuBodyItemInfo" style="cursor: auto;" id="ID-${item.id}">
                        <span id="ID-${item.id}">${item.name}</span><br/>
                    </div>
                    <div class="menuBodyItemButt">
                        <div class="menuBodyItemButtDel" id="ID-${item.id}"></div>
                    </div>
                </div>
            </c:forEach>
            <input type="hidden" name="partitionId" id="deleteKey" />
        </form:form>
    </div>
</div>
<input type="hidden" name="selectedPageNum" id="pageInformation" value="${model.selectedPageNum}"/>
<script type="text/javascript" src="<c:url value="/resources/js/adminUsers.js" />"></script>
</body>
</html>
